package pl.ciesla.ryd.users.core.application.port.input

import pl.ciesla.ryd.users.core.application.dto.UserRegistrationRequest

interface UserRegistrationUseCase {

    fun registerNewUser(user: UserRegistrationRequest)
}