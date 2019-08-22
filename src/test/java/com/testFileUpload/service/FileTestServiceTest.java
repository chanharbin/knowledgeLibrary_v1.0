package com.testFileUpload.service;

import com.testFileUpload.App;
import com.testFileUpload.util.FileUtil;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.*;


@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class FileTestServiceTest {
    @Autowired
    private FileTestService fileTestService;
    @Autowired
    private FileServiceForSpider fileServiceForSpider;
    @Test
    public void addFile() throws IOException {
        /*File file = new File("C:/Users/chenhaobin6/Desktop/2.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        fileTestService.addFile(multipartFile);*/
        MultipartFile multipartFile = FileUtil.transferFile("C:/Users/chenhaobin6/Desktop/2.txt");
        //fileServiceForSpider.uploadFile(multipartFile,"12","131","21",1);

    }
}