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
    public List<Material> selectMaterialByProject(String projectNo) {
        return materialMapper.selectMaterialByProject(projectNo);
    }

    @Override
    public int addMaterial(Material material) {
        HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        User user= (User)session.getAttribute("user");
        material.setCreateTime(new Date());
        material.setProposerNo(user.getId());
        material.setProposer(user.getUserName());
        material.setStatus(1);
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
            status=2;
        }else if("材料主管".equals(user.getRolerName())){
            status=1;
        }
        return materialMapper.selectMaterialByStatus(status);
    }
}
