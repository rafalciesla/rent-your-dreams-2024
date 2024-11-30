package pl.ciesla.ryd.gatewayserver.filters

import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component

@Component
class FilterUtility {

    companion object {
        internal const val CORRELATION_ID = "ryd-correlation-id"
    }

    fun getCorrelationId(requestHeaders: HttpHeaders): String? {
        if (requestHeaders.containsKey(CORRELATION_ID)) {
            return requestHeaders[CORRELATION_ID]!![0]
        }
        return null
    }
}