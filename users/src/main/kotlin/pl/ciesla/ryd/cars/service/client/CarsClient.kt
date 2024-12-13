package pl.ciesla.ryd.cars.service.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import pl.ciesla.ryd.lib.web.dto.CarDTO

@FeignClient(name = "cars", fallback = CarsFallback::class)
interface CarsClient {

    @GetMapping(value = ["/v1"], consumes = ["application/json"]) //TODO: URL MAPPING FROM CONFIG!
    fun getCars(): ResponseEntity<List<CarDTO>>
}