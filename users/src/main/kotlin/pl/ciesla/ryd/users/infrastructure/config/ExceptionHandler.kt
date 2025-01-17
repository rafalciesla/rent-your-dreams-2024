package pl.ciesla.ryd.users.infrastructure.config

import org.springframework.web.bind.annotation.ControllerAdvice
import pl.ciesla.ryd.lib.exception.RydExceptionHandler

@ControllerAdvice
class ExceptionHandler : RydExceptionHandler()