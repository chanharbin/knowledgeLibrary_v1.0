package com.testFileUpload.service;

import com.testFileUpload.App;
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
public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addPoint() throws Exception {
        userService.addPoint("4f98451a-7755-4cd1-bcb7-e4320b2493a4");
    }
}