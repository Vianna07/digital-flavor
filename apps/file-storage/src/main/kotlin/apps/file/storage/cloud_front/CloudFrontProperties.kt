package apps.file.storage.cloud_front

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aws.cloud-front")
class CloudFrontProperties {
    lateinit var domain: String
}
