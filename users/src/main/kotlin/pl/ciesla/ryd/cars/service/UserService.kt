package pl.ciesla.ryd.cars.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.ciesla.ryd.cars.mapper.UserMapper
import pl.ciesla.ryd.cars.model.User
import pl.ciesla.ryd.cars.repository.UserRepository
import pl.ciesla.ryd.cars.service.client.CarsClient
import pl.ciesla.ryd.lib.service.RYDService
import pl.ciesla.ryd.lib.web.dto.UserDTO

@Transactional
@Service
class UserService(
    override val repository: UserRepository,
    override val mapper: UserMapper,
    val carsClient: CarsClient
) : RYDService<User, UserDTO>(repository, mapper) {

    //TODO: remove when real logic is implemented.
    @Deprecated("Just for early testing")
    fun findAllUsersWithCars(): List<UserDTO> {
        return listOf(
            UserDTO(
                "usr",
                "test@test.com",
                "test",
                carsClient.getCars().body!!
            )
        )
    }
}