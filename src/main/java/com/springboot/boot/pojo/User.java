package com.springboot.boot.pojo;

import java.util.Date;

public class User {

    private Integer id;
    private String userName;
    private String userPass;
    private String phone;
    private Date loginTime;
    private String projectList;
    private String rolerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getProjectList() {
        return projectList;
    }

    public void setProjectList(String projectList) {
        this.projectList = projectList;
    }

    public String getRolerName() {
        return rolerName;
    }

    public void setRolerName(String rolerName) {
        this.rolerName = rolerName;
    }
}