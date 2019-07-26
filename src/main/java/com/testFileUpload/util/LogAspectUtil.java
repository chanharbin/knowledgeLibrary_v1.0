package com.testFileUpload.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * AOP实现日志功能
 */
@Aspect
@Component
public class LogAspectUtil {

    private final Logger logger = LoggerFactory.getLogger(LogAspectUtil.class);

    /**
     * 切入点描述，controller包的切入点
     */
    @Pointcut("execution(public * com.testFileUpload.controller..*.*(..))")
    public void controllerLog(){//切入点的名称
    }

    /**
     * 在切入点的方法之前run要做的
     * @param joinPoint
     */
    @Before("controllerLog()")
    public void logBeforeController(JoinPoint joinPoint){
        //用来获取请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)requestAttributes).getRequest();
        logger.info("********URL: " + httpServletRequest.getRequestURL().toString());
        logger.info("********HTTP_METHOD: " + httpServletRequest.getMethod());
        logger.info("********IP: " + httpServletRequest.getRemoteAddr());
        logger.info("********THE ARGS OF THE CONTROLLER: " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 方法执行结束之后打印日志，返回参数，捕捉异常等
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "controllerLog()",throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("连接点方法为：" + methodName + "，参数为：" + args + ",异常为：" + ex);
    }
}
