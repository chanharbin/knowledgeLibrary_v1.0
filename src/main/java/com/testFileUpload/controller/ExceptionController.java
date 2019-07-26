package com.testFileUpload.controller;

import com.testFileUpload.aop.LogAnnotation;
import com.testFileUpload.common.ResultObject;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {

   /* // 捕捉 CustomRealm 抛出的异常
    @ExceptionHandler(AccountException.class)
    public String handleShiroException(Exception ex) {
        return ex.getMessage();
    }*/

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    @LogAnnotation
    public ResultObject<ExceptionController> handle401() {
        return ResultObject.makeFail("您无权限访问");
    }

}