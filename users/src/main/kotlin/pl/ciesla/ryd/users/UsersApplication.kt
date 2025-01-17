package pl.ciesla.ryd.users

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import pl.ciesla.ryd.lib.annotation.RydBean

@SpringBootApplication
@ComponentScan(
    includeFilters = [ComponentScan.Filter(
        type = FilterType.ANNOTATION,
        classes = [RydBean::class]
    )])
class UsersApplication

fun main(args: Array<String>) {
    runApplication<pl.ciesla.ryd.users.UsersApplication>(*args)
}
