package pl.ciesla.ryd.users.infrastructure.adapter.input.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pl.ciesla.ryd.users.core.application.dto.UserRegistrationRequest
import pl.ciesla.ryd.users.core.application.port.input.UserRegistrationUseCase

@RestController
class UserController(
    private val userRegistrationUseCase: UserRegistrationUseCase
) {

    @PostMapping("/v1/register")
    fun registerNewUser(@RequestBody user: UserRegistrationRequest) : ResponseEntity<Void> {
        userRegistrationUseCase.registerNewUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}