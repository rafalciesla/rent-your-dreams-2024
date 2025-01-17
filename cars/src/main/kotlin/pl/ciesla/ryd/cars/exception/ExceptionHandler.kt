package pl.ciesla.ryd.cars.exception

import org.springframework.web.bind.annotation.ControllerAdvice
import pl.ciesla.ryd.lib.exception.RydExceptionHandler

@ControllerAdvice
class ExceptionHandler : RydExceptionHandler()