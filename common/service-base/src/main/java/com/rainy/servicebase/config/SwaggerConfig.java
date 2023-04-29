package com.rainy.servicebase.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(Environment environment) {
        //设置Swagger在哪个环境显示
        Profiles p = Profiles.of("dev", "test");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境中
        boolean flag = environment.acceptsProfiles(p);

        return new Docket(DocumentationType.OAS_30)
                //根据flag的true和false两个值自动设置Swagger显示的环境
                .enable(flag)
                //定义接口组名称
                .groupName("bruce-springboot-api")
                //调用下面apiInfo方法获取基本信息
                .apiInfo(apiInfo())
                //通过.select()方法，去配置扫描接口
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("com"))
                //指定路径处理,PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档标题")
                .description("Swagger3接口描述")
                .contact(new Contact("rainy", "作者网站(www.baidu.com)", "rainysteven1@gmail.com"))
                .version("1.0")
                .build();
    }

}


