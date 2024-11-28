package pl.ciesla.ryd.cars.config

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component("AuditConfig")
class AuditConfig : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of<String?>("IMAGES_MS") //TODO: change to user based info
    }
}