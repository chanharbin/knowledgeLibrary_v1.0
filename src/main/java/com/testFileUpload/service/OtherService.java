package com.testFileUpload.service;

import com.testFileUpload.aop.LogAnnotation;
import com.testFileUpload.aop.LogForSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OtherService {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;
    @Autowired
    private FileServiceForSpider fileServiceForSpider;

    @LogForSpider
    @Transactional(propagation = Propagation.REQUIRED)
    public void uploadManyFiles() throws Exception {
        for (int i = 0; i < 10; i++) {
//            if(i == 5){
//                throw new RuntimeException();
//            }
            fileServiceForSpider.uploadFile("20190802155208_1.txt","123","1","123",12);
            userService.addPoint("85851eea-1584-4c51-8a9d-eaa5903fc60a");
        }
    }
}
