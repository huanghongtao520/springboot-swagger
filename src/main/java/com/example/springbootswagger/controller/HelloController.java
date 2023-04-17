package com.example.springbootswagger.controller;

import com.example.springbootswagger.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Api(tags = "控制器类")
@Controller
public class HelloController {

    //项目始终有一个默认请求/error
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }
    //只要我们接口中返回值存在实体类，就会扫描到swagger中
    //@ApiOperation作用在方法上
    @ApiOperation("Hello请求")
    @PostMapping("/hello")
    public User user(User user){
        return new User();
    }
}
