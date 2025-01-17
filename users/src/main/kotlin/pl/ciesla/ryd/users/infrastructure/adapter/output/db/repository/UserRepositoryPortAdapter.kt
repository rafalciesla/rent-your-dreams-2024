package pl.ciesla.ryd.users.infrastructure.adapter.output.db.repository

import org.springframework.data.repository.findByIdOrNull
import pl.ciesla.ryd.lib.annotation.RydBean
import pl.ciesla.ryd.users.core.application.port.output.UserRepositoryPort
import pl.ciesla.ryd.users.infrastructure.adapter.output.db.model.UserEntity

@RydBean
class UserRepositoryPortAdapter(
    private val userJpaRepository: UserJpaRepository
) : UserRepositoryPort {

    override fun registerNewUser(userEntity: UserEntity) {
        userJpaRepository.save(userEntity)
    }

    override fun getUserById(id: Long): UserEntity? {
        return userJpaRepository.findByIdOrNull(id)
    }

    override fun getUserByEmail(email: String): UserEntity? {
        return userJpaRepository.findByEmail(email)
    }
}