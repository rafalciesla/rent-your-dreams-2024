package pl.ciesla.ryd.lib.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Embeddable
class Audit(

    @CreatedDate
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null,

    @CreatedBy
    @Column(updatable = false)
    var createdBy: String? = null,

    @LastModifiedDate
    @Column(insertable = false)
    var lastUpdatedAt: LocalDateTime? = null,

    @LastModifiedBy
    @Column(insertable = false)
    var lastUpdatedBy: String? = null
)