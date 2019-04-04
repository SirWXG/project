package com.springboot.boot.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Material {
    private Integer id;

    private String materialName;

    private String materialType;

    private Integer materialNum;

    private BigDecimal materialPrice;

    private Long materialTotalPrice;

    private String proposer;

    private Integer proposerNo;

    private Date createTime;

    private Integer approverNo;

    private String approverName;

    private Date approverTime;

    private Integer status;

    private Integer projectNo;

    private String projectName;

    private Long buyPrice;

    private String materialVar;

    private String materialUnit;

    private String note;

    private String materialSupplier;

    private String isFailer;

    public String getIsFailer() {
        return isFailer;
    }

    public void setIsFailer(String isFailer) {
        this.isFailer = isFailer;
    }

    public String getMaterialSupplier() {
        return materialSupplier;
    }

    public void setMaterialSupplier(String materialSupplier) {
        this.materialSupplier = materialSupplier;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMaterialVar() {
        return materialVar;
    }

    public void setMaterialVar(String materialVar) {
        this.materialVar = materialVar;
    }

    public Long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType == null ? null : materialType.trim();
    }

    public Integer getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(Integer materialNum) {
        this.materialNum = materialNum;
    }

    public BigDecimal getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(BigDecimal materialPrice) {
        this.materialPrice = materialPrice;
    }

    public Long getMaterialTotalPrice() {
        return materialTotalPrice;
    }

    public void setMaterialTotalPrice(Long materialTotalPrice) {
        this.materialTotalPrice = materialTotalPrice;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer == null ? null : proposer.trim();
    }

    public Integer getProposerNo() {
        return proposerNo;
    }

    public void setProposerNo(Integer proposerNo) {
        this.proposerNo = proposerNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getApproverNo() {
        return approverNo;
    }

    public void setApproverNo(Integer approverNo) {
        this.approverNo = approverNo;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    public Date getApproverTime() {
        return approverTime;
    }

    public void setApproverTime(Date approverTime) {
        this.approverTime = approverTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(Integer projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }
}