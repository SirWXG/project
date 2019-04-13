package com.springboot.boot.service.impl;

import com.springboot.boot.mapper.MaterialMapper;
import com.springboot.boot.pojo.Material;
import com.springboot.boot.pojo.User;
import com.springboot.boot.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public String selectPrice(String materialName) {
        return materialMapper.selectPrice(materialName);
    }

    @Override
    public List<Material> selectMaterial(String materialName) {
        return materialMapper.selectMaterial(materialName);
    }

    @Override
    public List<Material> selectMaterialByProject(Map<String,Object> map) {
        return materialMapper.selectMaterialByProject(map);
    }

    @Override
    public int addMaterial(Material material) {
        HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        User user= (User)session.getAttribute("user");
        material.setCreateTime(new Date());
        material.setProposerNo(user.getId());
        material.setProposer(user.getUserName());
        material.setStatus(0);
        long price = material.getBuyPrice()*material.getMaterialNum();
        material.setMaterialTotalPrice(price);
        return materialMapper.addMaterial(material);
    }

    @Override
    public List<Material> selectMaterialByStatus() {
        HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        User user = (User)session.getAttribute("user");
        Integer status = 0;
        if("经理".equals(user.getRolerName())){
            status=1;
        }else if("材料主管".equals(user.getRolerName())){
            status=0;
        }
        return materialMapper.selectMaterialByStatus(status);
    }

    @Override
    public int updateMaterial(Material material) {
        HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        User user = (User)session.getAttribute("user");
        material.setApproverTime(new Date());
        material.setApproverNo(user.getId());
        material.setApproverName(user.getUserName());
        return materialMapper.updateMaterial(material);
    }

    @Override
    public int updateMaterialNum(Material material) {
        return materialMapper.updateMaterialNum(material);
    }

    @Override
    public List<Material> selectMaterialForStatus() {
        HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        User user = (User)session.getAttribute("user");
        return materialMapper.selectMaterialForStatus(user.getUserName());
    }

    @Override
    public List<Material> selectMaterialById(int id) {
        return materialMapper.selectMaterialById(id);
    }
}
