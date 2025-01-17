package pl.ciesla.ryd.lib.common.cars.dto

data class UserDto(

    var username: String,
    var email: String,
    var password: String,
    var rentedCars: List<CarDto>
)
