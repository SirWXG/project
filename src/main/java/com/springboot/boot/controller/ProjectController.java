package com.springboot.boot.controller;

import com.springboot.boot.utils.SimpleResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    public SimpleResult getAllProject(){
        return SimpleResult.getSimple(1,"",3,"");
    }
}
