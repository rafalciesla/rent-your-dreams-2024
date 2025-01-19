package pl.ciesla.ryd.users.infrastructure.adapter.output.client

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import pl.ciesla.ryd.lib.common.cars.dto.CarDto

@Service
class CarsFallback: CarsClient {
    override fun getCars(): ResponseEntity<List<CarDto>> {
        return ResponseEntity.ok(listOf())
    }
}