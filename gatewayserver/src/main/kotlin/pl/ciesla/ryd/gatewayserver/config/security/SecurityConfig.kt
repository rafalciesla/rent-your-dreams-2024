package pl.ciesla.ryd.gatewayserver.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.reactive.config.WebFluxConfigurer
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    val roleConverter: KeycloakRoleConverter
) : WebFluxConfigurer {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        val securityWebFilter = http.authorizeExchange { ex ->
            ex.pathMatchers(HttpMethod.GET, "/actuator").permitAll()
                .pathMatchers("/ryd/cars/**").permitAll()
                .pathMatchers("/ryd/images/**").permitAll()
                .pathMatchers("/ryd/users/**").authenticated()
        }.oauth2ResourceServer { spec ->
            spec.jwt { jwtSpec ->
                jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())
            }
        }

        return securityWebFilter.build()
    }

    private fun grantedAuthoritiesExtractor(): Converter<Jwt, Mono<AbstractAuthenticationToken>> {
        val jwtAuthConverter = JwtAuthenticationConverter()
        jwtAuthConverter.setJwtGrantedAuthoritiesConverter(roleConverter)

        return ReactiveJwtAuthenticationConverterAdapter(jwtAuthConverter)
    }
}