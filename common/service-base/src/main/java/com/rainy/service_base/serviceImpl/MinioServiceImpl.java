package com.rainy.service_base.serviceImpl;

import com.rainy.service_base.handler.MinioTemplate;
import com.rainy.service_base.service.MinioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
public class MinioServiceImpl implements MinioService {
    @Autowired
    private MinioTemplate minioTemplate;
    @Value("${minio.bucket-name}")
    private final String bucketName = "images";

    /**
     * 获取文件外链
     *
     * @param objectName 文件名称
     * @return url
     */
    @Override
    public String getObjectURL(String objectName, int expiry) {
        try {
            return minioTemplate.getObjectURL(bucketName, objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String uploadFile(InputStream inputStream, String fileName, String contentType, String folder) {
        String path = null;
        try {
            System.out.println(fileName);
            fileName = String.format("%s/%s.%s", folder, fileName, contentType2suffix(contentType));
            minioTemplate.putObject(bucketName, fileName, inputStream, contentType);
            path = minioTemplate.getObjectURL(bucketName, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    private String contentType2suffix(String contentType) {
        String suffix = "";
        switch (contentType) {
            case "image/jpeg":
                suffix = "jpg";
                break;
            case "image/jpg":
                suffix = "jpg";
        }
        return suffix;
    }
}
