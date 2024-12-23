package pl.ciesla.ryd.gatewayserver.web.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RequestMapping
@RestController
class FallbackController {

    @GetMapping(FALLBACK_URI)
    fun serviceError(): Mono<String> {
        return Mono.just("An error occurred, please try again later.") // TODO: configure better fallbacks
    }

    companion object {
        const val FALLBACK_URI: String = "/fallback"
    }
}