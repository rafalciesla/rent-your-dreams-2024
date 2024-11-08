package pl.ciesla.ryd.cars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditConfig")
class UsersApplication

fun main(args: Array<String>) {
    runApplication<UsersApplication>(*args)
}
