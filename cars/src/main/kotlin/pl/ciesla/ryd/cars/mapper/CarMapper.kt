package pl.ciesla.ryd.cars.mapper

import org.mapstruct.Mapper
import pl.ciesla.ryd.cars.model.Car
import pl.ciesla.ryd.lib.mapper.RYDMapper
import pl.ciesla.ryd.lib.web.dto.CarDTO

@Mapper(componentModel = "spring")
interface CarMapper : RYDMapper<Car, CarDTO>