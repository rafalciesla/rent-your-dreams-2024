package pl.ciesla.ryd.cars.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pl.ciesla.ryd.cars.repository.CarRepository

@Transactional
@Service
class CarService(
    val repository: CarRepository
)