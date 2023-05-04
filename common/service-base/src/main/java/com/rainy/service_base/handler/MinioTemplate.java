package com.rainy.service_base.handler;

import com.rainy.service_base.config.MinioProperties;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Component
@Configuration
@EnableConfigurationProperties(com.rainy.service_base.config.MinioProperties.class)
public class MinioTemplate {
    @Autowired
    private MinioProperties minioProperties;

    private MinioClient minioClient;

    public MinioTemplate() {
    }

    public MinioClient getMinioClient() {
        if (minioClient == null) {
            return MinioClient.builder()
                    .endpoint(minioProperties.getUrl())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                    .build();
        }
        return minioClient;
    }

    public void createBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, ServerException, XmlParserException, ErrorResponseException {
        MinioClient minioClient = getMinioClient();
        BucketExistsArgs existsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
        MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();
        if (!minioClient.bucketExists(existsArgs)) {
            minioClient.makeBucket(makeBucketArgs);
        }
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket 名称
     * @param objectName 文件名称
     * @return
     */
    public String getObjectURL(String bucketName, String objectName, int expiry) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return getMinioClient().getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .expiry(expiry, TimeUnit.DAYS)
                .build());
    }

    public String getObjectURL(String bucketName, String path) {
        return String.format("%s/%s/%s", minioProperties.getUrl(), bucketName, path);
    }


    /**
     * 获取文件
     *
     * @param bucketName
     * @param objectName
     * @return
     */
    public InputStream getObject(String bucketName, String objectName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, ErrorResponseException, ServerException, XmlParserException {
        return getMinioClient().getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 上传文件
     *
     * @param bucketName
     * @param objectName
     * @param stream
     */
    public void putObject(String bucketName, String objectName, InputStream stream, String contentType) throws IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, ErrorResponseException, InsufficientDataException, InternalException, ServerException, XmlParserException {
        createBucket(bucketName);
        getMinioClient().putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .contentType(contentType)
                .stream(stream, stream.available(), -1).build());
    }

    /**
     * 删除文件
     *
     * @param bucketName
     * @param objectName
     */
    public void removeObject(String bucketName, String objectName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, ErrorResponseException, ServerException, XmlParserException {
        getMinioClient().removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

}
