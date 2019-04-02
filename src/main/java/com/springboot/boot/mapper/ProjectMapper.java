package com.springboot.boot.mapper;

import com.springboot.boot.pojo.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    List<Project> selectProject(String array[]);
}