package pl.ciesla.ryd.cars.model

import jakarta.persistence.*
import lombok.Data
import lombok.EqualsAndHashCode
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import pl.ciesla.ryd.lib.model.RYDEntity
import java.math.BigDecimal

@Entity
@Table(name = "cars")
@Data
@EntityListeners(AuditingEntityListener::class)
@EqualsAndHashCode(callSuper = true)
class Car(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cars_seq")
    @SequenceGenerator(name = "cars_seq", initialValue = 100, allocationSize = 1)
    var id: Long? = null,
    var brand: String,
    var model: String,
    var yearOfManufacture: Int,
    var engineType: String,
    var engineCapacity: Int,
    var price: BigDecimal

): RYDEntity()