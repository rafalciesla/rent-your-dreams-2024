package pl.ciesla.ryd.cars.web.controller

import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.ciesla.ryd.cars.service.ImageService

@RestController
@RequestMapping("/api/v1/images")
class ImageController(val imageService: ImageService) {

    @PostMapping("/upload")
    fun uploadFile(@RequestParam file: MultipartFile): ResponseEntity<String> =
        ResponseEntity(imageService.saveFile(file), HttpStatus.OK)

    @GetMapping("/download")
    fun downloadFile(@RequestParam fileId: String): ResponseEntity<Resource> {
        val inputStream = imageService.downloadFile(fileId)
        return ResponseEntity.ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"img.png\""
            ) //TODO: change to original filename
            .contentLength(inputStream.available().toLong())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(InputStreamResource(inputStream))
    }

}