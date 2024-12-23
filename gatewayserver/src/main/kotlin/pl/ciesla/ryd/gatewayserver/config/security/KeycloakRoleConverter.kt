package pl.ciesla.ryd.gatewayserver.config.security

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@Component
class KeycloakRoleConverter : Converter<Jwt, Collection<GrantedAuthority>> {

    override fun convert(source: Jwt): Collection<GrantedAuthority> {
        @Suppress("UNCHECKED_CAST") val realmAccess: Map<String, Any> =
            source.claims["realm_access"] as Map<String, Any>? ?: return ArrayList()

        @Suppress("UNCHECKED_CAST") val roles =
            realmAccess["roles"] as List<String>? ?: return ArrayList()

        return roles.stream()
            .map { roleName -> "ROLE_$roleName" }
            .map(::SimpleGrantedAuthority)
            .toList()
    }
}