package com.springboot.boot.service.impl;

import com.springboot.boot.mapper.MaterialMapper;
import com.springboot.boot.pojo.Material;
import com.springboot.boot.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
