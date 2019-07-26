package com.testFileUpload.controller;

import com.testFileUpload.common.ResultObject;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//普通登陆用户
@RestController
@RequestMapping("/user")
public class UserController{

    @GetMapping("/getMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultObject<UserController> getMessage() {
        return ResultObject.makeSuccess("您拥有该权限，您可以访问所有接口");
    }
}