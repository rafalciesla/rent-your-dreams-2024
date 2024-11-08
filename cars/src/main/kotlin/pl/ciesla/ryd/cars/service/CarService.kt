package pl.ciesla.ryd.cars.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.ciesla.ryd.cars.mapper.CarMapper
import pl.ciesla.ryd.cars.model.Car
import pl.ciesla.ryd.cars.repository.CarRepository
import pl.ciesla.ryd.cars.web.dto.CarDTO
import pl.ciesla.ryd.lib.service.RYDService

@Transactional
@Service
class CarService(
    override val repository: CarRepository,
    override val mapper: CarMapper
) : RYDService<Car, CarDTO>(repository, mapper) {

}