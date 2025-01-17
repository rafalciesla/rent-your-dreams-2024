package pl.ciesla.ryd.lib.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class RydExceptionHandler {

    @ExceptionHandler
    fun handleException(ex: RydException, webRequest: WebRequest): ResponseEntity<FrontendException> {
        println("Exception in path: ${webRequest.contextPath}")
        return ResponseEntity<FrontendException>(FrontendException(ExceptionType.MINOR, ex.message ?: "nomessage"), ex.httpStatus)
    }
}