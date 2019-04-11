package com.springboot.boot.mapper;

import com.springboot.boot.pojo.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MaterialMapper {

    String selectPrice(@Param("materialName")String materialName);

    List<Material> selectMaterial(@Param("materialName")String materialName);

    List<Material> selectMaterialByProject(Map<String,Object> map);

    int addMaterial(Material material);

    List<Material> selectMaterialByStatus(@Param("status")Integer status);

    int updateMaterial(Material material);

    int updateMaterialNum(Material material);
}