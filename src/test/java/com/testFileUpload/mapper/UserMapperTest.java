package com.testFileUpload.mapper;

import com.alibaba.fastjson.JSON;
import com.testFileUpload.App;
import com.testFileUpload.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        User user = userMapper.selectByUserName("11");
        System.out.println(JSON.toJSONString(user));
    }
}