package com.testFileUpload.service;

import com.testFileUpload.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class OtherServiceTest {
    @Autowired
    private OtherService otherService;

    @Test
    public void uploadManyFiles() throws Exception {
        otherService.uploadManyFiles();
    }
}