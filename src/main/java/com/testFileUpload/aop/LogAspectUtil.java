package com.testFileUpload.aop;

import com.testFileUpload.util.JwtUtil;
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
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Pointcut("@annotation(com.testFileUpload.aop.LogAnnotation)")
    public void controllerLog(){//切入点的名称
    }

    /**
     * 在切入点的方法之前run要做的
     * @param joinPoint
     */
    @Before("controllerLog()")
    public void logBefore(JoinPoint joinPoint) throws ClassNotFoundException {
        //用来获取请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)requestAttributes).getRequest();
        String userName = JwtUtil.getUsername(httpServletRequest.getHeader("token"));
        if (userName == null || ("".equals(userName))){
            logger.warn("用户为空");
        }
        String beanName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        logger.info("UserName：" + userName + " URL: " + httpServletRequest.getRequestURL().toString() + "HTTP_METHOD:" + httpServletRequest.getMethod()
        + "IP:" + httpServletRequest.getRemoteAddr() + "beanName：" + beanName + "methodName：" + methodName);
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
        logger.error("连接点方法为：" + methodName + "，参数为：" + args + ",异常为：" + ex);
    }

    /**
     * 获取注解内容
     * */
    private Map<String,String> getLogMark(JoinPoint joinPoint) throws ClassNotFoundException {
        Map<String,String> map = new HashMap<>();
        String methodName = joinPoint.getSignature().getName();
        String targetName = joinPoint.getTarget().getClass().getName();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods){
            if(method.getName().equals(methodName)){
                LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
                map.put("targetType",logAnnotation.targetType());
                map.put("action",logAnnotation.action());
                map.put("remark",logAnnotation.remark());
            }
        }
        return map;
    }
}
