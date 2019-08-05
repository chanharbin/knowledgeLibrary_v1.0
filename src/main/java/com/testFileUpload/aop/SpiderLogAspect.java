package com.testFileUpload.aop;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.testFileUpload.pojo.Log;
import com.testFileUpload.service.LogService;
import com.testFileUpload.util.JWTToken;
import com.testFileUpload.util.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Aspect
@Component
public class SpiderLogAspect {
//    private Logger logger = LoggerFactory.getLogger(WebLogAcpect.class);

    @Autowired
    private LogService logService;
    private Log rlog = new Log();

    @Pointcut("@annotation(com.testFileUpload.aop.LogForSpider)")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        rlog.setLogId(String.valueOf(UUID.randomUUID()));
        rlog.setRequestUrl(request.getRequestURI().toString());
        rlog.setMethod(request.getMethod());
        rlog.setParams(Arrays.toString(joinPoint.getArgs()));
        Date date = new Date();
        rlog.setOperateDate(date);

        String userId ="1";
        rlog.setUserId(userId);
        // 记录下请求内容
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        rlog.setResultParams("String");
        rlog.setDescription("Success");
        rlog.setLogType("Info");
        logService.uploadLog(rlog);
    }

    @AfterThrowing(pointcut="webLog()",throwing="e")
    public void doAfterThrowing(Exception e){
        rlog.setLogType("Exception");
        rlog.setDescription(e.getMessage());
        rlog.setResultParams("String");
        logService.uploadLog(rlog);
    }
}
