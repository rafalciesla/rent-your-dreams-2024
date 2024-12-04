package pl.ciesla.ryd.cars.service.client

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import pl.ciesla.ryd.lib.web.dto.CarDTO

@Service
class CarsFallback: CarsClient {
    override fun getCars(): ResponseEntity<List<CarDTO>> {
        return ResponseEntity.ok(listOf())
    }
}