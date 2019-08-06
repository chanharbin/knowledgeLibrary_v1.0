package com.testFileUpload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileTestService {
    public void addFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String path = "D:/FileForCopy/" + fileName;
        File file1 = new File(path);
        file.transferTo(file1);
    }
}
