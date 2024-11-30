package pl.ciesla.ryd.cars.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import pl.ciesla.ryd.cars.exception.StorageException
import pl.ciesla.ryd.cars.model.Image
import pl.ciesla.ryd.cars.repository.ImageRepository
import java.io.IOException
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*
import kotlin.io.path.absolutePathString
import kotlin.io.path.exists

@Service
class ImageService(
    val imageRepository: ImageRepository
) {
    private val imagesLocation = Paths.get("/home/revayne/ryd_files/images") //TODO: move to properties

    init {
        try {
            Files.createDirectories(imagesLocation)
        } catch (ex: IOException) {
            throw StorageException(ex.message)
        }
    }

    @Transactional
    fun saveFile(file: MultipartFile): String {
        return try {
            val originalFilename = file.originalFilename ?: ""
            if (file.isEmpty || originalFilename.isEmpty()) throw StorageException("ERROR: File is empty")

            val systemFileName = originalFilename.replace(" ", "-")
            val destinationFile = getDestinationFile(systemFileName)
            validateDestinationFile(destinationFile)

            file.inputStream.use { inputStream ->
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING)
            }

            saveToDBAndGetDownloadId(systemFileName, destinationFile)
        } catch (ex: IOException) {
            throw StorageException("${ex.javaClass}: ${ex.message}") //TODO: modify and handle
        }
    }

    private fun saveToDBAndGetDownloadId(fileName: String, filePath: Path): String {
        val downloadId = UUID.randomUUID()
        val image = Image(null, fileName, filePath.absolutePathString(), downloadId)
        imageRepository.save(image)
        return downloadId.toString()
    }

    private fun getDestinationFile(originalFilename: String): Path {
        return imagesLocation.resolve(Paths.get(originalFilename))
            .normalize()
            .toAbsolutePath()
    }

    private fun validateDestinationFile(destinationFile: Path) {
        if (destinationFile.parent != imagesLocation.toAbsolutePath()) {
            throw StorageException("ERROR: Cannot store file outside specified directory")
        }
    }

    fun downloadFile(string: String): InputStream {
        val image = imageRepository.findImageByFileDownloadId(UUID.fromString(string))
        val imageFile = Paths.get(image.filePath)
        when {
            imageFile.exists() -> return Files.newInputStream(imageFile)
            else -> throw StorageException("File does not exist!")
        }
    }
}