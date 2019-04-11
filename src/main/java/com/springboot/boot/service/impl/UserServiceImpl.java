package com.springboot.boot.service.impl;

import com.springboot.boot.mapper.UserMapper;
import com.springboot.boot.pojo.User;
import com.springboot.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> checkLogin(Map<String, Object> map) {
        return userMapper.checkLogin(map);
    }

    @Override
    public List<User> selectUser(String userName) {
        return userMapper.selectUser(userName);
    }

    @Override
    public List<User> selectMaterialManger() {
        return userMapper.selectMaterialManger();
    }

    @Override
    public int updateProjectList(Map<String, Object> map) {
        return userMapper.updateProjectList(map);
    }

    @Override
    public int updateManagerList(String projectList) {
        return userMapper.updateManagerList(projectList);
    }
}
