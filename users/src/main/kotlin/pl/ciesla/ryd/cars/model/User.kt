package pl.ciesla.ryd.cars.model

import jakarta.persistence.*
import lombok.Data
import lombok.EqualsAndHashCode
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import pl.ciesla.ryd.lib.model.RYDEntity
import java.math.BigDecimal

@Entity
@Table(name = "users")
@Data
@EntityListeners(AuditingEntityListener::class)
@EqualsAndHashCode(callSuper = true)
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", initialValue = 100, allocationSize = 1)
    var id: Long? = null,
    var username: String,
    var email: String,
    var password: String,

): RYDEntity()