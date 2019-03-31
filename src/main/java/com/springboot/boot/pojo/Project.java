package com.springboot.boot.pojo;

import java.util.Date;

public class Project {
    private Integer id;

    private String projectName;

    private Date createTime;

    private Double projectBalance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getProjectBalance() {
        return projectBalance;
    }

    public void setProjectBalance(Double projectBalance) {
        this.projectBalance = projectBalance;
    }
}