package pl.ciesla.ryd.lib.exception

import org.springframework.http.HttpStatus

open class RydException (message: String, val httpStatus: HttpStatus): RuntimeException(message)