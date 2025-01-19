package pl.ciesla.ryd.users.core.application.port.output

import pl.ciesla.ryd.users.infrastructure.adapter.output.db.model.UserEntity


interface UserRepositoryPort {

    fun registerNewUser(userEntity: UserEntity)

    fun getUserById(id: Long): UserEntity?

    fun getUserByEmail(email: String): UserEntity?
}