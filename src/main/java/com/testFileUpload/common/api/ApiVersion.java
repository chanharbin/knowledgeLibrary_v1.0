package com.testFileUpload.common.api;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * API版本控制注解
 * @author CAIFUCHENG3
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {

    /**
     * api版本
     * @return
     */
    int apiVersionValue() default 1;
}
