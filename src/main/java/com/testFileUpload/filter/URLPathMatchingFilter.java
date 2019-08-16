package com.testFileUpload.filter;

import com.testFileUpload.common.ApplicationContextProvider;
import com.testFileUpload.service.PermissionService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class URLPathMatchingFilter extends PathMatchingFilter {
    private PermissionService permissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        if (null == permissionService)
            this.permissionService = ApplicationContextProvider.getBean(PermissionService.class);

        String requestURI = getPathWithinApplication(request);
        System.out.println("requestURI:" + requestURI);

        Subject subject = SecurityUtils.getSubject();
        // 如果没有登录，就跳转到登录页面
        if (!subject.isAuthenticated()) {
            WebUtils.issueRedirect(request, response, "/login");
            return false;
        }

        // 看看这个路径权限里有没有维护，如果没有维护，一律放行(也可以改为一律不放行)
        boolean needInterceptor = permissionService.needInterceptor(requestURI);
        if (!needInterceptor) {
            throw new UnauthorizedException("系统暂无" + requestURI + "的权限限制，请设置");
        }
        else {
            if(subject.isPermitted(requestURI)){
                return true;
            }
            else {
                UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");
                throw ex;
            }
        }
    }
}
