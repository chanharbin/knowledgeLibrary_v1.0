package com.testFileUpload.controller;

import com.testFileUpload.aop.LogAnnotation;
import com.testFileUpload.common.ResultObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//游客

@RestController
@RequestMapping("/guest")
public class GuestController{
    @LogAnnotation
    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public ResultObject login() {
        return ResultObject.makeSuccess("欢迎进入，您的身份是游客");
    }

    @LogAnnotation
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultObject submitLogin() {
        return ResultObject.makeSuccess("你有游客权限，您拥有获得该接口的信息的权限");
    }
}