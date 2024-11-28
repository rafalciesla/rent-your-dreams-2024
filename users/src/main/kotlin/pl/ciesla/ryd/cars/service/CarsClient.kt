package pl.ciesla.ryd.cars.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import pl.ciesla.ryd.lib.web.dto.CarDTO

@FeignClient("cars")
interface CarsClient {

    @GetMapping(value = ["/v1/cars"], consumes = ["application/json"])
    fun getCars(): ResponseEntity<List<CarDTO>>
}