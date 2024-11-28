package apps.file.storage.s3

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/s3")
class S3Controller(private val s3service: S3Service) {

    @PostMapping("/upload")
    suspend fun uploadFile(
        @RequestParam("canteenId") canteenId: String,
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<String> {
        return ResponseEntity.ok(s3service.putS3Object(canteenId + "/" + file.originalFilename, file))
    }
}