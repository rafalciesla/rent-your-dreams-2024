package pl.ciesla.ryd.cars.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Cars API",
        description = "",
        version = "v1",
        contact = Contact(
            name = "Rafał Cieśla",
            email = "rafal.ciesla404@gmail.com",
            url = "https://www.linkedin.com/in/rafal-ciesla/"
        ),
        license = License(
            name = "License name",
            url = "license url"
        )
    )
)
class OpenApiConfiguration { //TODO: add more and populate to another microservices
}