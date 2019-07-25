package com.testFileUpload.controller;

import com.testFileUpload.common.BaseController;
import com.testFileUpload.common.ResultObject;
import com.testFileUpload.common.error.common.Errors;
import com.testFileUpload.common.error.server.CommonError;
import com.testFileUpload.mapper.UserMapper;
import com.testFileUpload.pojo.User;
import com.testFileUpload.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController extends BaseController {
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
    public ResultObject login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        User user = userMapper.selectByUserName(username);
        if(user == null){
            throw Errors.wrap(CommonError.UNEXPECTED);
        }
        String basePassword = user.getUserPwd();
        String salt = user.getSalt();
        String userId = user.getUserId();
        String passwordEncoded = new SimpleHash("md5",password,salt,2).toString();
        if (basePassword == null) {
            return ResultObject.makeFail("登录失败");
        } else if (!basePassword.equals(passwordEncoded)) {
            return ResultObject.makeFail("密码错误");
        } else {
            return ResultObject.makeSuccess(JwtUtil.createToken(userId,username),"登录成功");
        }
    }
}