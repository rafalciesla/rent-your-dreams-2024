package pl.ciesla.ryd.gatewayserver.filters

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Mono

@Configuration
class ResponseTraceFilter(
    private val filterUtility: FilterUtility
) {

    val logger = KotlinLogging.logger {}

    @Bean
    fun postGlobalFilter(): GlobalFilter {
        return GlobalFilter { exchange, chain ->
            chain.filter(exchange).then(Mono.fromRunnable {
                val requestHeaders = exchange.request.headers
                val correlationId = filterUtility.getCorrelationId(requestHeaders)
                logger.debug { "Updated the correlation id to the outbound headers: $correlationId" }
                exchange.response.headers.add(FilterUtility.CORRELATION_ID, correlationId)
            })
        }
    }
}