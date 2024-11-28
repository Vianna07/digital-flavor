package apps.file.storage.s3

import apps.file.storage.cloud_front.CloudFrontProperties
import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.content.ByteStream
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class S3Service(private val s3Properties: S3Properties, private val cloudFrontProperties: CloudFrontProperties) {

    val s3Client = S3Client {
        region = s3Properties.region
        credentialsProvider = StaticCredentialsProvider {
            accessKeyId = s3Properties.accessKeyId
            secretAccessKey = s3Properties.secretAccessKey
        }
    }

    suspend fun putS3Object(objectKey: String, file: MultipartFile): String {
        val request = PutObjectRequest {
            bucket = s3Properties.bucketName
            key = objectKey
            body = ByteStream.fromBytes(file.bytes)
        }

        val response = s3Client.putObject(request)

        println("Arquivo $objectKey enviado com sucesso.")
        println("ETag do arquivo: ${response.eTag}")

        return "${cloudFrontProperties.domain}/$objectKey"
    }

}