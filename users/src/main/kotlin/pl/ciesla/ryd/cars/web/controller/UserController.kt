package pl.ciesla.ryd.cars.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.ciesla.ryd.cars.model.User
import pl.ciesla.ryd.cars.service.UserService
import pl.ciesla.ryd.lib.web.controller.RYDController
import pl.ciesla.ryd.lib.web.dto.UserDTO

@RestController
@RequestMapping("/v1")
class UserController(
    service: UserService
) : RYDController<User, UserDTO, UserService>(service) {

    //TODO: remove when real logic is implemented.
    @Deprecated("Just for early testing")
    @GetMapping("/with-cars")
    fun findAllWithCars(): ResponseEntity<List<UserDTO>> {
        val users = service.findAllUsersWithCars() //TODO: remove it! just for testing
        return ResponseEntity.ok().body(users)
    }
}