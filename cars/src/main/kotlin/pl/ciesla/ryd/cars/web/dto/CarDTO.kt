package pl.ciesla.ryd.cars.web.dto

import pl.ciesla.ryd.lib.web.dto.RYDDTO
import java.math.BigDecimal
import java.time.Year

data class CarDTO(

    var brand: String,
    var model: String,
    var yearOfManufacture: Int,
    var engineType: String,
    var engineCapacity: Int,
    var price: BigDecimal

) : RYDDTO()
