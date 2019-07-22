package com.testFileUpload.controller;

import com.testFileUpload.mapper.TestMapper;
import com.testFileUpload.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestMapper testMapper;
    @GetMapping(value = "/insert")
    public void insert(){
        Test test=new Test();
        test.setId(3);
        test.setName("chb");
        testMapper.insert(test);
    }
}
