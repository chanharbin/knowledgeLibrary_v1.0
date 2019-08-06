package com.testFileUpload.util;

import org.apache.http.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.mock.web.MockMultipartFile;

public class FileUtil {
    public static MultipartFile transferFile(String fileAbsolutePath) throws IOException {
        File file = new File(fileAbsolutePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        return multipartFile;
    }
}
