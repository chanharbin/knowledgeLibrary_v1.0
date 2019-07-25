package com.testFileUpload.common.api;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Api 规则配置：自定义url匹配逻辑
 * @author CAIFUCHENG3
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    /**
     * api前缀调用规则
     */
    private static final Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");

    /**
     * api 版本
     */
    private int apiVersion;

    /**
     * 有参构造函数
     * @param apiVersion
     */
    public ApiVersionCondition(int apiVersion){
        this.apiVersion = apiVersion;
    }
    /**
     * 合并不同的筛选条件
     * Combine this condition with another such as conditions from a
     * type-level and method-level {@code @RequestMapping} annotation.
     *
     * @param other the condition to combine with.
     * @return a request condition instance that is the result of combining
     * the two condition instances.
     */
    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVersionCondition(other.apiVersion);
    }

    /**
     * Check if the condition matches the request returning a potentially new
     * instance created for the current request. For example a condition with
     * multiple URL patterns may return a new instance only with those patterns
     * that match the request.
     * <p>For CORS pre-flight requests, conditions should match to the would-be,
     * actual request (e.g. URL pattern, query parameters, and the HTTP method
     * from the "Access-Control-Request-Method" header). If a condition cannot
     * be matched to a pre-flight request it should return an instance with
     * empty content thus not causing a failure to match.
     * 根据 httpServletRequest 查找匹配的筛选条件
     * @param httpServletRequest
     * @return a condition instance in case of a match or {@code null} otherwise.
     */
    @Nullable
    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        Matcher matcher = VERSION_PREFIX_PATTERN.matcher(httpServletRequest.getRequestURI());
        if(matcher.find()){
            Integer version = Integer.valueOf(matcher.group(1));
            if(version >= this.apiVersion)
            {
                return this;
            }
        }
        return null;
    }

    /**
     * Compare this condition to another condition in the context of
     * a specific request. This method assumes both instances have
     * been obtained via {@link #getMatchingCondition(HttpServletRequest)}
     * to ensure they have content relevant to current request only.
     * 比较不同筛选条件,用于排序
     * @param other
     * @param request
     */
    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        //优先匹配最新的版本号
        return other.getApiVersion() - this.apiVersion;
    }

    /**
     * 获取当前api版本
     * @return
     */
    public int getApiVersion(){
        return apiVersion;
    }
}
