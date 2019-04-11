package com.springboot.boot.service;

import com.springboot.boot.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> checkLogin(Map<String,Object> map);

    List<User> selectUser(String userName);

    List<User> selectMaterialManger();

    int updateProjectList(Map<String,Object> map);

    int updateManagerList(String projectList);
}
