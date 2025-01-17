package pl.ciesla.ryd.users.core.application.dto

data class UserRegistrationRequest(
    val username: String?,
    val password: String,
    val email: String,
    val name: String?,
    val surname: String?,
)