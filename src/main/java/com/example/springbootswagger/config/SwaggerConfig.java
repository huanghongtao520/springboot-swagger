package com.example.springbootswagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Controller;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.function.Predicate;

import static springfox.documentation.service.ApiInfo.DEFAULT_CONTACT;

@Configuration
@EnableSwagger2 //开启Swagger
public class SwaggerConfig {
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("分组1");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("分组2");
    }

    //配置Swagger的Bean实例
    // select，api,build是配套使用的，中间不能插入其他
    @Bean
    public Docket docket(Environment environment){
        //设置要显示的swagger环境
        Profiles profiles=Profiles.of("dev","test");
        //判断所处环境,在配置文件 spring.profiles.active=test指定为test，则flag为true，开启swagger
        boolean flag=environment.acceptsProfiles(profiles);
        System.out.println(flag);
        //通过上面两行配置，可以使swagger在测试时生效，发布时失效

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //默认是开启swagger
                .enable(flag)
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basepackage指定要扫描的包
                //.any()扫描全部，none（）都不扫描，
                // withClassAnnotation扫描类上的注解
                // withMethodAnnotation(Controller.class)扫描类上有Controller注解
                .apis(RequestHandlerSelectors.basePackage("com.example.springbootswagger"))
                //过滤什么路径，只扫描某个路径
                //.paths(PathSelectors.ant("/路径"))
                .build();
    }
    //定义页面显示的内容
    //配置Swagger信息apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("黄宏涛","www.baidu.com","3044349929@qq.com");
        return new ApiInfo("黄宏涛的接口文档",
                "文档描述",
                "v1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());

    }
}
