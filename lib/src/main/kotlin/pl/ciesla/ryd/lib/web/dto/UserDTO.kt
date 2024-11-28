package pl.ciesla.ryd.lib.web.dto

data class UserDTO(

    var username: String,
    var email: String,
    var password: String,
    var rentedCars: List<CarDTO>

) : RYDDTO()
