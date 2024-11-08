package pl.ciesla.ryd.cars.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.ciesla.ryd.cars.mapper.UserMapper
import pl.ciesla.ryd.cars.model.User
import pl.ciesla.ryd.cars.repository.UserRepository
import pl.ciesla.ryd.cars.web.dto.UserDTO
import pl.ciesla.ryd.lib.service.RYDService

@Transactional
@Service
class UserService(
    override val repository: UserRepository,
    override val mapper: UserMapper
) : RYDService<User, UserDTO>(repository, mapper) {

}