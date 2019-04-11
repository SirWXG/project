package com.springboot.boot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.pojo.Project;
import com.springboot.boot.pojo.User;
import com.springboot.boot.service.ProjectService;
import com.springboot.boot.service.UserService;
import com.springboot.boot.utils.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/selectProject")
    public PageInfo<Project> getAllProject(@RequestParam(name = "page",defaultValue = "1")Integer page,
            @RequestParam(name = "size",defaultValue = "10")Integer size,HttpSession session){
        User user = (User) session.getAttribute("user");
        String projectList = user.getProjectList();
        PageHelper.startPage(page,size);
        List<Project> list = projectService.selectProject(projectList);
        PageInfo<Project> info = new PageInfo<>(list,4);
        return info;
    }

    @GetMapping(value = "selectProjects")
    public SimpleResult getProject(HttpSession session){
        SimpleResult result = new SimpleResult();
        User user = (User) session.getAttribute("user");
        String projectList = user.getProjectList();
        List<Project> list = projectService.selectProject(projectList);
        result.setData(list);
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

    @RequestMapping(value = "/selectProjectByUser",method = RequestMethod.POST)
    @ResponseBody
    public SimpleResult selectProjectByUser(@RequestParam(name = "userName")String userName){
        SimpleResult result = new SimpleResult();
        List<User> list = userService.selectUser(userName);
        if("材料主管".equals(list.get(0).getRolerName())||"经理".equals(list.get(0).getRolerName())){
            result.setCode(1);
        }else{
            String project_list = list.get(0).getProjectList();
            List<Project> list_project = projectService.selectProject(project_list);
            result.setData(list_project);
            result.setCode(0);
        }
        return result;
    }

    @RequestMapping(value = "/addProject",method =RequestMethod.POST )
    @ResponseBody
    public SimpleResult addProject(Project project,HttpSession session){
        SimpleResult result = new SimpleResult();
        project.setMaterialManager(userService.selectMaterialManger().get(0).getUserName());
        int flag = projectService.addProject(project);
        String project_list = userService.selectMaterialManger().get(0).getProjectList();
        project_list=project_list+","+project.getId();
        userService.updateManagerList(project_list);
        User user = (User)session.getAttribute("user");
        user.setProjectList(project_list);
        session.setAttribute("user",user);
        if(flag<1){
            result.setMsg("添加项目出错");
        }else{
            result.setCode(1);
        }
        return result;
    }
}
