package pl.ciesla.ryd.users.infrastructure.config

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component("AuditConfig")
class AuditConfig : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of<String?>("USERS_MS") //TODO: change to user based info
    }
}