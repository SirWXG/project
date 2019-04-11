package com.springboot.boot.service;

import com.springboot.boot.pojo.Project;

import java.util.List;

public interface ProjectService {

    List<Project> selectProject(String arrayList);

    int addProject(Project project);
}
