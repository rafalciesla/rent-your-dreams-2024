package pl.ciesla.ryd.users.infrastructure.adapter.output.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.ciesla.ryd.users.infrastructure.adapter.output.db.model.UserEntity

interface UserJpaRepository : JpaRepository<UserEntity, Long> {

    fun findByEmail(email: String): UserEntity?
}