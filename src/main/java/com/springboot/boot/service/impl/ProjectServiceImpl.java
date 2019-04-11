package com.springboot.boot.service.impl;

import com.springboot.boot.mapper.ProjectMapper;
import com.springboot.boot.mapper.UserMapper;
import com.springboot.boot.pojo.Project;
import com.springboot.boot.pojo.User;
import com.springboot.boot.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Project> selectProject(String arrayList) {
        String array[] = arrayList.split(",");
        return projectMapper.selectProject(array);
    }

    @Override
    public int addProject(Project project) {
        HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        project.setCreateTime(new Date());
        project.setProjectBalance(0.00);
        project.setProjectPro(((User)session.getAttribute("user")).getUserName());
        int flag = projectMapper.addProject(project);
        return flag;
    }
}
