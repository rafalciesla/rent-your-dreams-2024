package pl.ciesla.ryd.cars.web.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.ciesla.ryd.cars.model.Car
import pl.ciesla.ryd.cars.service.CarService
import pl.ciesla.ryd.lib.web.controller.RYDController
import pl.ciesla.ryd.lib.web.dto.CarDTO

@RestController
@RequestMapping("/v1")
class CarController(
    service: CarService
) : RYDController<Car, CarDTO, CarService>(service)