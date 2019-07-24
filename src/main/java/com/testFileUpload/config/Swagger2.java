package com.testFileUpload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()  //RequestHandlerSelectors.any()，所有的路劲都去扫描
                //这个扫描包的意思一样，固定路劲就去相应的路劲扫描
                .apis(RequestHandlerSelectors.basePackage("com.testFileUpload.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //就是对生成的api文档的一个描述
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文档标题")
                .description("文档描述")
                .termsOfServiceUrl("相关url")
                .version("版本：1.0")
                .build();
    }
}