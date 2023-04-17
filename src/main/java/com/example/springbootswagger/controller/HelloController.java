package com.example.springbootswagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //项目始终有一个默认请求/error
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }
}
