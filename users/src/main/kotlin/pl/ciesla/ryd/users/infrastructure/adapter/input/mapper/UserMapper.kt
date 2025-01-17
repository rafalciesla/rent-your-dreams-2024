package pl.ciesla.ryd.users.infrastructure.adapter.input.mapper

import pl.ciesla.ryd.users.core.domain.model.User
import pl.ciesla.ryd.users.infrastructure.adapter.output.db.model.UserEntity

class UserMapper {

    fun domainToEntity(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            username = user.username,
            password = user.password,
            email = user.email,
            name = user.name,
            surname = user.surname
        )
    }

    fun entityToDomain(user: UserEntity): User {
        return User(
            id = user.id,
            username = user.username,
            password = user.password,
            email = user.email,
            name = user.name,
            surname = user.surname
        )
    }
}