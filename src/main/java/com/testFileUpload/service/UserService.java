
package com.testFileUpload.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.testFileUpload.aop.LogForSpider;
import com.testFileUpload.mapper.UserMapper;
import com.testFileUpload.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserService {
   @Autowired
   private UserMapper userMapper;
   public User findUserById(String userId){
       User user = userMapper.selectById(userId);
       return user;
   }
   public User findByUsername(User user){
       String userName = user.getUserName();
       EntityWrapper<User> wrapper = new EntityWrapper<User>();
       wrapper.eq("user_name",userName);
       List<User> users = userMapper.selectList(wrapper);
       return users.get(0);
   }

   public void insertUser(User user){
       userMapper.insert(user);
   }
   @LogForSpider
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public void addPoint(String userId) throws Exception {
       User user = userMapper.selectByUserId(userId);
       if(user == null){
           throw new Exception("用户不存在");
       }
       user.setPoint(user.getPoint() + 1);
       userMapper.updateById(user);
   }
}

