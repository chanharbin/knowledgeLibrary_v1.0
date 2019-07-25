package com.testFileUpload.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.testFileUpload.mapper.UserMapper;
import com.testFileUpload.pojo.User;
import com.testFileUpload.service.UserService;
import com.testFileUpload.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class SignupController {
    @Autowired
    private UserService userService;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    public SignupController(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String signUp() {
        return "sign_up";
    }

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param password_confirm 密码确认
     * @param email 邮箱
     * @param sex 性别
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username, @RequestParam("password") String password,
                       @RequestParam("password_confirm") String password_confirm,
                       @RequestParam("email") String email, @RequestParam("sex") String sex,
                           @RequestParam("role")String role){
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("user_name",username);
        List<User> users = userMapper.selectList(wrapper);
        if(users.isEmpty()){
            if(!password.equals(password_confirm)){
                return "密码确认失败";
            }
            else {
                String salt = new SecureRandomNumberGenerator().nextBytes().toString(); //盐量随机
                String encodedPassword= new SimpleHash("md5",password,salt,2).toString();
                Date date = new Date();
                User user = new User(username,encodedPassword,sex,email,salt);
                user.setAddTime(date);
                user.setUserId(String.valueOf(UUID.randomUUID()));
                user.setRole(role);
                user.setPoint(1);
                user.setPhoto("111");
                user.setSalt("978dheugfyrjs");
                userService.insertUser(user);
            }
        }
        else{
            return "用户已注册";
        }
        return "注册成功！";
    }
}
