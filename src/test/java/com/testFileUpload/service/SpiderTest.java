package com.testFileUpload.service;

import com.testFileUpload.App;
import com.testFileUpload.spider.SpiderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class SpiderTest {

    @Autowired
    private SpiderService spiderService;


    @Test
    public void add() throws Exception {
        spiderService.add();
    }
}
