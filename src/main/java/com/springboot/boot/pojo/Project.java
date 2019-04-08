package com.springboot.boot.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Project {
    private Integer id;

    private String projectName;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    private Double projectBalance;

    private String projectPro;

    private String materialManager;

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

    public String getProjectPro() {
        return projectPro;
    }

    public void setProjectPro(String projectPro) {
        this.projectPro = projectPro;
    }

    public String getMaterialManager() {
        return materialManager;
    }

    public void setMaterialManager(String materialManager) {
        this.materialManager = materialManager;
    }
}