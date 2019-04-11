package com.springboot.boot.mapper;

import com.springboot.boot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    List<User> checkLogin(Map<String,Object> map);

    List<User> selectUser(@Param("userName")String userName);

    List<User> selectMaterialManger();

    int updateProjectList(Map<String,Object> map);

    int updateManagerList(@Param("projectList")String projectList);
}