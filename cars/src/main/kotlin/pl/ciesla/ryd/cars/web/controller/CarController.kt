package pl.ciesla.ryd.cars.web.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.ciesla.ryd.cars.service.CarService

@RestController
@RequestMapping("/v1")
class CarController(
    service: CarService
)