package com.springboot.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping(value = "/hello")
    public String sayHello(){
        return "demo";
    }

    @RequestMapping(value = "/index")
    public String toBefore(){
        return "index";
    }
}
