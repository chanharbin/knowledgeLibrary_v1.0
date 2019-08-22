package com.testFileUpload.service.impl;

import com.testFileUpload.App;
import com.testFileUpload.common.ApplicationContextProvider;
import com.testFileUpload.mapper.PermissionMapper;
import com.testFileUpload.service.PermissionService;
import org.aspectj.lang.annotation.AfterThrowing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class PermissionServiceImplTest {
    private PermissionService permissionService;
    @Test
    public void listPermissions() {
        permissionService = ApplicationContextProvider.getBean(PermissionService.class);
        System.out.println(permissionService.listPermissions("1234"));
    }

    @Test
    public void needIntecepter(){
        permissionService = ApplicationContextProvider.getBean(PermissionService.class);
        boolean b = permissionService.needInterceptor("/getAllFile");
        System.out.println(b);
    }
}