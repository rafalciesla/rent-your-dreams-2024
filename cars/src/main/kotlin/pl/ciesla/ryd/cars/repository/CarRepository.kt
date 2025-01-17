package pl.ciesla.ryd.cars.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.ciesla.ryd.cars.model.Car

interface CarRepository : JpaRepository<Car, Long>