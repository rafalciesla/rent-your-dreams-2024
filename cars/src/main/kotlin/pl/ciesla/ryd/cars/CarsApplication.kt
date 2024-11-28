package pl.ciesla.ryd.cars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "AuditConfig")
@SpringBootApplication
class CarsApplication

fun main(args: Array<String>) {
    runApplication<CarsApplication>(*args)
}
