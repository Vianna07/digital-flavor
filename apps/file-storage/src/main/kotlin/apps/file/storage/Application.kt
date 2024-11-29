package apps.file.storage

import apps.file.storage.cloud_front.CloudFrontProperties
import apps.file.storage.s3.S3Properties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(S3Properties::class, CloudFrontProperties::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
