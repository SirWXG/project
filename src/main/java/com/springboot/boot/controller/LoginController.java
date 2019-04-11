package com.springboot.boot.controller;

import com.springboot.boot.pojo.User;
import com.springboot.boot.service.UserService;
import com.springboot.boot.utils.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/checkLogin")
    public SimpleResult getLogin(@RequestParam(name = "userName")String userName,
                                 @RequestParam(name = "password")String password,
            @RequestParam(name = "projectName",defaultValue = "")String projectName,HttpSession session){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName",userName);
        map.put("userPass",password);
        List<User> list =  userService.checkLogin(map);
        if(list.size()<1){
            return SimpleResult.getSimple(0,"账户名或密码错误,请重新输入");
        }else if(list.get(0).getRolerName().equals("普通员工")&&projectName.trim().length()<1){
            return SimpleResult.getSimple(0,"请选择项目");
        }
        session.setAttribute("projectName",projectName);
        session.setAttribute("user",list.get(0));
        return SimpleResult.getSimple(1,"");
    }
}
