package com.testFileUpload.mapper;

import com.testFileUpload.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class PermissionMapperTest {
    @Autowired
    private PermissionMapper permissionMapper;
    @Test
    public void listPermissionsByUserName() {
       permissionMapper.listPermissionsByUserName("123");
    }
}