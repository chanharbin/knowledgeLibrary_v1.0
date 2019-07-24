package com.testFileUpload;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.testFileUpload.mapper.UserMapper;
import com.testFileUpload.pojo.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private UserMapper userMapper;
    @org.junit.Test
    public void test(){
        /*User user = userMapper.selectById(1);
        System.out.println(user.getUserName());*/
        /*User user = new User();
        user.setUserName("chb");
        User user1 = userMapper.selectOne(user);
        System.out.println(JSON.toJSONString(user1));*/
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("user_name","chb");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(JSON.toJSONString(users));
    }
}
