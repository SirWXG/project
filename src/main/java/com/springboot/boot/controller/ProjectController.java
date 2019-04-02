package com.springboot.boot.controller;

import com.springboot.boot.pojo.Project;
import com.springboot.boot.pojo.User;
import com.springboot.boot.service.ProjectService;
import com.springboot.boot.utils.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/selectProject")
    public SimpleResult getAllProject(HttpSession session){
        SimpleResult result = new SimpleResult();
        User user = (User) session.getAttribute("user");
        String projectList = user.getProjectList();
        List<Project> list = projectService.selectProject(projectList);
        result.setCount(list.size());
        List<Project> lists = projectService.selectProject(projectList);
        result.setData(lists);
        result.setCode(0);
        result.setMsg("");
        return result;
    }

    @PostMapping(value = "/updateProject")
    public SimpleResult updateProject(HttpSession session){
        SimpleResult result = new SimpleResult();
        User user = (User) session.getAttribute("user");
        if("项目经理".equals(user.getRolerName())||"材料主管".equals(user.getRolerName())){

        }else{
            return  SimpleResult.getSimple(1,"无法操作");
        }
        return result;
    }
}
