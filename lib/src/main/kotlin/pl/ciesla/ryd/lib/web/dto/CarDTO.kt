package pl.ciesla.ryd.lib.web.dto

import pl.ciesla.ryd.lib.common.cars.EngineType
import pl.ciesla.ryd.lib.common.cars.TransmissionType
import java.math.BigDecimal

data class CarDTO(

    var brand: String,
    var model: String,
    var yearOfManufacture: Int,
    var engineLayout: String,
    var engineCapacity: Int,
    var engineType: EngineType,
    var transmissionType: TransmissionType,
    var horsePower: Int,
    var timeTo100: Double,
    var color: String,
    var numberOfDoors: Int,
    var numberOfSeats: Int,
    var pricePerDay: BigDecimal,
    var description: String

) : RYDDTO()
