package pl.ciesla.ryd.function

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.ciesla.ryd.dto.NotificationDTO
import java.util.function.Function

private val logger = KotlinLogging.logger {}

@Configuration
class NotificationFunctions {

    @Bean
    fun email(): Function<NotificationDTO, NotificationDTO> {
        return Function { notification ->
            logger.info { "email notification function for email: ${notification.email}" }
            notification
        }
    }

    @Bean
    fun sms(): Function<NotificationDTO, NotificationDTO> {
        return Function { notification ->
            logger.info { "sms notification function for number: ${notification.phoneNumber}" }
            notification
        }
    }
}