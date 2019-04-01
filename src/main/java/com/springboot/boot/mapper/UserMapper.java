package com.springboot.boot.mapper;

import com.springboot.boot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<User> checkLogin(Map<String,Object> map);
}