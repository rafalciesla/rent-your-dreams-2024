package pl.ciesla.ryd.cars.web.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.ciesla.ryd.cars.model.User
import pl.ciesla.ryd.cars.service.UserService
import pl.ciesla.ryd.cars.web.dto.UserDTO
import pl.ciesla.ryd.lib.web.controller.RYDController

@RestController
@RequestMapping("/v1/users")
class UserController(
    service: UserService
) : RYDController<User, UserDTO>(service) {

}