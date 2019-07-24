package com.testFileUpload.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.testFileUpload.mapper.UserMapper;
import com.testFileUpload.pojo.User;
import com.testFileUpload.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    private final UserMapper userMapper;
    @Autowired
    public LoginController(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public String notLogin() {
        return "您尚未登陆";
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public String notRole() {
        return "您没有权限";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "成功注销";
    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        EntityWrapper<User> wrapper = new EntityWrapper<User>();
        wrapper.eq("user_name",username);
        List<User> users = userMapper.selectList(wrapper);
        long userId = users.get(0).getUserId();
        String basePassword = users.get(0).getUserPwd();
        if (basePassword == null) {
            return "用户名错误";
        } else if (!basePassword.equals(password)) {
            return "密码错误";
        } else {
            return "登录成功" + "jwt:" + JwtUtil.createToken(userId,username);
        }
    }
}