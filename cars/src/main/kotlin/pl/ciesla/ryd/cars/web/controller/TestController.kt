package pl.ciesla.ryd.cars.web.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class MyTestController {

    @Value("\${app.profile-name:difolt}")
    lateinit var profileName : String

    @GetMapping
    fun getAppProfile(): String {
        return profileName
    }
}