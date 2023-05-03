package com.rainy.servicebase.serviceImpl;

import com.rainy.servicebase.handler.MinioTemplate;
import com.rainy.servicebase.service.MinioService;
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

    private final String bucketName = "images";

    /**
     * 获取文件外链
     *
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     */
    public String getObjectURL(String objectName, int expires) {
        try {
            return minioTemplate.getObjectURL(bucketName, objectName, expires);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String uploadFile(InputStream inputStream, String fileName, String contentType) {
        String path = null;
        try {
            minioTemplate.putObject(bucketName, fileName, inputStream, contentType);
            path = fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
