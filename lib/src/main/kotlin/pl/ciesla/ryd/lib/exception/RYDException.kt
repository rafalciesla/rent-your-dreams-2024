package pl.ciesla.ryd.lib.exception

import org.springframework.http.HttpStatus

open class RYDException (message: String, val httpStatus: HttpStatus): Exception(message) {

}