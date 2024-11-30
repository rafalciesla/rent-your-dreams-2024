package pl.ciesla.ryd.gatewayserver.filters

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Order(1)
@Component
class RequestTraceFilter(
    private val filterUtility: FilterUtility
) : GlobalFilter {

    val logger = KotlinLogging.logger {}

    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
        val headers = exchange?.request?.headers
        if (isCorrelationIdPresent(headers!!)) {
            logger.info { "Correlation ID found in the incoming request: ${filterUtility.getCorrelationId(headers)}" }
        } else {
            exchange.request.mutate().header(FilterUtility.CORRELATION_ID, generateCorrelationId()).build()
            logger.info { "No correlation ID found in the incoming request. Generating a new one ${filterUtility.getCorrelationId(headers)}" }
        }
        return chain?.filter(exchange) ?: Mono.empty()
    }

    private fun isCorrelationIdPresent(headers: HttpHeaders): Boolean {
        return filterUtility.getCorrelationId(headers) != null
    }

    private fun generateCorrelationId(): String {
        return java.util.UUID.randomUUID().toString()
    }
}