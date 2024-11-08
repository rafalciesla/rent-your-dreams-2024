package pl.ciesla.ryd.lib.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import pl.ciesla.ryd.lib.exception.ExceptionDTO
import pl.ciesla.ryd.lib.exception.ExceptionType
import pl.ciesla.ryd.lib.exception.RYDException

@ControllerAdvice
class RYDExceptionHandler {

    @ExceptionHandler
    fun handleException(ex: RYDException, webRequest: WebRequest): ResponseEntity<ExceptionDTO> {
        println("Exception in path: ${webRequest.contextPath}")
        return ResponseEntity<ExceptionDTO>(ExceptionDTO(ExceptionType.MINOR, ex.message ?: "nomessage"), ex.httpStatus)
    }
}