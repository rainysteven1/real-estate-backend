package com.rainy.servicebase.service;

import java.io.InputStream;

public interface MinioService {

    String getObjectURL(String objectName, int expiry);

    String uploadFile(InputStream inputStream, String fileName, String contentType, String folder);
}
