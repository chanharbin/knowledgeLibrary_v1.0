/*
package com.testFileUpload.controller;

import com.alibaba.fastjson.JSONObject;

import com.testFileUpload.pojo.User;
import com.testFileUpload.service.TokenService;
import com.testFileUpload.service.UserService;
import com.testFileUpload.util.UserLoginToken;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    //登录
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "userPwd", value = "用户密码",dataType = "String",paramType = "query")
    })
    @ApiOperation(value = "用户登录", httpMethod = "POST", response = ResponseEntity.class)
    @PostMapping("/login")
    public Object login(User user){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findByUsername(user);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getUserPwd().equals(user.getUserPwd())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }
    @ApiImplicitParams( @ApiImplicitParam(name = "token", value = "Authorization token",
            required = true, dataType = "string", paramType = "header"))
    @ApiOperation(value = "验证token", httpMethod = "GET", response = ResponseEntity.class)
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){

        return "你已通过验证";
    }
}
*/
