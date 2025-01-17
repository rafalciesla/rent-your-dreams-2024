package pl.ciesla.ryd.cars.model

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*

@Table(name = "images")
@EntityListeners(AuditingEntityListener::class)
@Entity
class Image(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "images_seq")
    @SequenceGenerator(name = "images_seq", initialValue = 100, allocationSize = 1)
    var id: Long? = null,
    var fileName: String,
    var filePath: String,
    var fileDownloadId: UUID

)