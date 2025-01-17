package pl.ciesla.ryd.users.infrastructure.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableFeignClients(basePackages = ["pl.ciesla.ryd.users"])
@EnableJpaAuditing(auditorAwareRef = "AuditConfig")
@Configuration
class GlobalConfig