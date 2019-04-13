package com.springboot.boot.service;


import com.springboot.boot.pojo.Material;
import java.util.List;
import java.util.Map;

public interface MaterialService {

    String selectPrice(String materialName);

    List<Material> selectMaterial(String materialName);

    List<Material> selectMaterialByProject(Map<String,Object> map);

    int addMaterial(Material material);

    List<Material> selectMaterialByStatus();

    int updateMaterial(Material material);

    int updateMaterialNum(Material material);

    List<Material> selectMaterialForStatus();

    List<Material> selectMaterialById(int id);
}
