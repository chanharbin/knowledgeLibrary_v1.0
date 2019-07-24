
package com.testFileUpload.service;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.testFileUpload.mapper.UserMapper;
import com.testFileUpload.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
   /* @Autowired
    private UserDOMapper userDOMapper;

    public User findUserById(String userId) {
        User userById = userDOMapper.findUserById(userId);
        return userById;
    }

    public User findByUsername(User user) {
        User byUserName = userDOMapper.findByUserName(user.getUsername());
        return byUserName;
    }*/
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

}

