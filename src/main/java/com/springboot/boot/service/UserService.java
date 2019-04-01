package com.springboot.boot.service;

import com.springboot.boot.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> checkLogin(Map<String,Object> map);
}
