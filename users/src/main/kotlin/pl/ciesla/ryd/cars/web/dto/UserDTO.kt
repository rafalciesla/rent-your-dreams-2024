package pl.ciesla.ryd.cars.web.dto

import pl.ciesla.ryd.lib.web.dto.RYDDTO

data class UserDTO(

    var username: String,
    var email: String,
    var password: String,

) : RYDDTO()
