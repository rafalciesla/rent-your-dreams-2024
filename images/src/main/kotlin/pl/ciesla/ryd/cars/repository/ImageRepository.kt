package pl.ciesla.ryd.cars.repository

import org.springframework.data.jpa.repository.JpaRepository
import pl.ciesla.ryd.cars.model.Image
import java.util.*

interface ImageRepository : JpaRepository<Image, Long> {
    fun findImageByFileDownloadId(downloadId: UUID): Image
}