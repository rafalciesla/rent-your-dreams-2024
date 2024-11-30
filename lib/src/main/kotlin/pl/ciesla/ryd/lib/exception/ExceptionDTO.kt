package pl.ciesla.ryd.lib.exception

data class ExceptionDTO(
    val exceptionType: ExceptionType,
    val message: String
)