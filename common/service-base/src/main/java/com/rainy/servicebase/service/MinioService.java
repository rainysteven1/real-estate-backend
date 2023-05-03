package com.rainy.servicebase.service;

import java.io.InputStream;

public interface MinioService {

    String getObjectURL(String objectName, int expires);

    String uploadFile(InputStream inputStream, String fileName, String contentType);
}
