package com.testFileUpload.service;

import com.testFileUpload.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class FileServiceForSpiderTest {
    @Autowired
    private FileServiceForSpider fileServiceForSpider;
    @Test
    public void uploadFile() throws IOException {
        fileServiceForSpider.uploadFile("1","1232132143213","1","12","576",7973);
    }
}