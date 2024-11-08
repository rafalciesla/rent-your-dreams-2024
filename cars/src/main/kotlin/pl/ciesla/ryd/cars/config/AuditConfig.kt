package pl.ciesla.ryd.cars.config

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.Optional

@Component("AuditConfig")
class AuditConfig : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of<String?>("CARS_MS") //TODO: change to user based info
    }
}