package pl.ciesla.ryd.lib.exception

data class FrontendException(
    val exceptionType: ExceptionType,
    val message: String
)