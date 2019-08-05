package com.testFileUpload.service;

import com.testFileUpload.App;
import com.testFileUpload.pojo.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class FileServiceTest {
    @Autowired
    private LogService logService;
    @Test
    public void test(){
        Log log = new Log();
        log.setLogId("123");
        log.setRequestUrl("1111");
        log.setResultParams("111");
        log.setDescription("1111");
        log.setLogType("11");
        log.setUserId("111");
        log.setOperateDate(new Date());
        log.setMethod("11");
        log.setParams("aa");
        logService.uploadLog(log);
    }

}