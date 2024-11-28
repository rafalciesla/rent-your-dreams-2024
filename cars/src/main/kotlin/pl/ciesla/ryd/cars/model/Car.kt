package pl.ciesla.ryd.cars.model

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import pl.ciesla.ryd.lib.common.cars.EngineType
import pl.ciesla.ryd.lib.common.cars.TransmissionType
import pl.ciesla.ryd.lib.model.RYDEntity
import java.math.BigDecimal

@Table(name = "cars")
@EntityListeners(AuditingEntityListener::class)
@Entity
class Car(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cars_seq")
    @SequenceGenerator(name = "cars_seq", initialValue = 100, allocationSize = 1)
    var id: Long? = null,
    @Enumerated(EnumType.STRING)
    var engineType: EngineType,
    @Enumerated(EnumType.STRING)
    var transmissionType: TransmissionType,
    var brand: String,
    var model: String,
    var yearOfManufacture: Int,
    var engineLayout: String,
    var engineCapacity: Int,
    var horsePower: Int,
    var timeTo100: Double,
    var color: String,
    var numberOfDoors: Int,
    var numberOfSeats: Int,
    var pricePerDay: BigDecimal,
    var description: String,

    ) : RYDEntity()