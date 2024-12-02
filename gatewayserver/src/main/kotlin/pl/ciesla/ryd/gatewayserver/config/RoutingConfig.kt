package pl.ciesla.ryd.gatewayserver.config

import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.Buildable
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
            }
            .uri("lb://USERS")
    }

    private fun configureCarsRoute(r: PredicateSpec): Buildable<Route> {
        return r.path("/ryd/cars/**")
            .filters { f ->
                f.rewritePath("/ryd/cars/(?<segment>.*)", "/\${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
            }
            .uri("lb://CARS")
    }

    private fun configureImagesRoute(r: PredicateSpec): Buildable<Route> {
        return r.path("/ryd/images/**")
            .filters { f ->
                f.rewritePath("/ryd/images/(?<segment>.*)", "/\${segment}")
                    .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
            }
            .uri("lb://IMAGES")
    }
}