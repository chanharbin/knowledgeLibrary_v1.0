package com.testFileUpload.filter;

import com.testFileUpload.util.JWTToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;



public class JwtFilter extends BasicHttpAuthenticationFilter {
        @Override
        protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
                                          Object mappedValue) throws UnauthorizedException {
            if (((HttpServletRequest) request).getHeader("Token") != null) {
                //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
                try {
                    executeLogin(request, response);
                    return true;
                } catch (Exception e) {                //token 错误
                    System.out.println(e.getMessage());
                }
            }
            return true;
            //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        }
        @Override
        protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String token = httpServletRequest.getHeader("Token");
            JWTToken jwtToken = new JWTToken(token);
            // 提交给realm进行登入，如果错误他会抛出异常并被捕获
            getSubject(request, response).login(jwtToken);
            // 如果没有抛出异常则代表登入成功，返回true
            return true;
        }
}

