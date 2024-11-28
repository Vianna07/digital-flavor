package apps.file.storage.s3

import jakarta.annotation.PostConstruct
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "aws.s3")
class S3Properties {
    lateinit var bucketName: String
    lateinit var region: String
    lateinit var accessKeyId: String
    lateinit var secretAccessKey: String
}
