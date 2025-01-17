package pl.ciesla.ryd.users.infrastructure.adapter.output.db.model

import jakarta.persistence.*
import lombok.Data
import lombok.EqualsAndHashCode
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import pl.ciesla.ryd.lib.model.Audit

@Entity
@Table(name = "users")
@Data
@EntityListeners(AuditingEntityListener::class)
@EqualsAndHashCode(callSuper = true)
class UserEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", initialValue = 100, allocationSize = 1)
    var id: Long? = null,
    var username: String,
    var email: String,
    var password: String,
    var name: String? = null,
    var surname: String? = null,

    @Embedded
    var audit: Audit = Audit()
)