package pl.ciesla.ryd.cars.mapper

import org.mapstruct.Mapper
import pl.ciesla.ryd.cars.model.Car
import pl.ciesla.ryd.cars.web.dto.CarDTO
import pl.ciesla.ryd.lib.mapper.RYDMapper

@Mapper(componentModel = "spring")
interface CarMapper : RYDMapper<Car, CarDTO> {
}