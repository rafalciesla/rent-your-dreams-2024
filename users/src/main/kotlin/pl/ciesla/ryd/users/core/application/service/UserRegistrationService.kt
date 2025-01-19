package pl.ciesla.ryd.users.core.application.service

import pl.ciesla.ryd.lib.annotation.RydBean
import pl.ciesla.ryd.users.core.application.dto.UserRegistrationRequest
import pl.ciesla.ryd.users.core.application.port.input.UserRegistrationUseCase
import pl.ciesla.ryd.users.core.application.port.output.UserRepositoryPort
import pl.ciesla.ryd.users.core.domain.exception.UserAlreadyExistsException
import pl.ciesla.ryd.users.infrastructure.adapter.output.db.model.UserEntity

@RydBean
class UserRegistrationService(
    private val userRepositoryPort: UserRepositoryPort
) : UserRegistrationUseCase {

    override fun registerNewUser(user: UserRegistrationRequest) {
        validateUser(user)
        val userEntity = getUserEntity(user)
        userRepositoryPort.registerNewUser(userEntity)
    }

    private fun validateUser(user: UserRegistrationRequest) {
        val userByEmail = userRepositoryPort.getUserByEmail(user.email)
        if (userByEmail != null) {
            throw UserAlreadyExistsException(user.email)
        }
    }

    private fun getUserEntity(user: UserRegistrationRequest) : UserEntity {
        return UserEntity(
            username = user.username ?: user.email,
            email = user.email,
            password = user.password
        )
    }
}