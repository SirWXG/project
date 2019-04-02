package com.springboot.boot.service.impl;

import com.springboot.boot.mapper.ProjectMapper;
import com.springboot.boot.pojo.Project;
import com.springboot.boot.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<Project> selectProject(String arrayList) {
        String array[] = arrayList.split(",");
        return projectMapper.selectProject(array);
    }
}
