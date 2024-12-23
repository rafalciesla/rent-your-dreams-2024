package pl.ciesla.ryd.gatewayserver.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.Buildable
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import pl.ciesla.ryd.gatewayserver.web.controller.FallbackController
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.LocalDateTime

@Configuration
class RoutingConfig {

    @Bean
    fun rydRouteConfig(routeLocatorBuilder: RouteLocatorBuilder): RouteLocator {
        return routeLocatorBuilder.routes()
            .route(::configureUsersRoute)
            .route(::configureCarsRoute)
            .route(::configureImagesRoute)
            .build()
    }

    private fun configureUsersRoute(r: PredicateSpec): Buildable<Route> {
        return r.path("/ryd/users/**")
            .filters { f ->
                f.rewritePath("/ryd/users/(?<segment>.*)", "/\${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                    .retry { retryConfig ->
                        retryConfig.setRetries(3)
                            .setMethods(HttpMethod.GET)
                            .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
                    }.circuitBreaker {
                        config -> config.setName("usersCircuitBreaker")
                    }.requestRateLimiter{ config ->
                        config.setRateLimiter(redisRateLimiter())
                            .setKeyResolver(userKeyResolver())
                    }
            }
            .uri("lb://USERS")
    }

    private fun configureCarsRoute(r: PredicateSpec): Buildable<Route> {
        return r.path("/ryd/cars/**")
            .filters { f ->
                f.rewritePath("/ryd/cars/(?<segment>.*)", "/\${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                    .retry { retryConfig ->
                        retryConfig.setRetries(3)
                            .setMethods(HttpMethod.GET)
                            .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
                    }.circuitBreaker { circuitBreakerConfig ->
                        circuitBreakerConfig.setName("carsCircuitBreaker")
                            .setFallbackUri("forward:${FallbackController.FALLBACK_URI}")
                    }.requestRateLimiter{ config ->
                        config.setRateLimiter(redisRateLimiter())
                            .setKeyResolver(userKeyResolver())
                    }
            }
            .uri("lb://CARS")
    }

    private fun configureImagesRoute(r: PredicateSpec): Buildable<Route> {
        return r.path("/ryd/images/**")
            .filters { f ->
                f.rewritePath("/ryd/images/(?<segment>.*)", "/\${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                    .retry { retryConfig ->
                        retryConfig.setRetries(3)
                            .setMethods(HttpMethod.GET)
                            .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)
                    }.circuitBreaker {
                        config -> config.setName("imagesCircuitBreaker")
                    }.requestRateLimiter{ config ->
                        config.setRateLimiter(redisRateLimiter())
                            .setKeyResolver(userKeyResolver())
                    }
            }
            .uri("lb://IMAGES")
    }

    @Bean
    fun defaultCircuitBreakerCustomizer(): Customizer<ReactiveResilience4JCircuitBreakerFactory> {
        return Customizer { factory: ReactiveResilience4JCircuitBreakerFactory ->
            factory.configureDefault { id ->
                Resilience4JConfigBuilder(id)
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
                    .build()
            }
        }
    }

    @Bean
    fun redisRateLimiter(): RedisRateLimiter {
        return RedisRateLimiter(1, 1, 1)
    }

    @Bean
    fun userKeyResolver(): KeyResolver {
        return KeyResolver { exchange -> Mono.justOrEmpty(exchange.request.headers.getFirst("user"))
            .defaultIfEmpty("anonymous") }
    }
}