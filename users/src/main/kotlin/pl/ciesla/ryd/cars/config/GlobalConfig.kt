package pl.ciesla.ryd.cars.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableFeignClients(basePackages = ["pl.ciesla.ryd.cars"])
@EnableJpaAuditing(auditorAwareRef = "AuditConfig")
@Configuration
class GlobalConfig