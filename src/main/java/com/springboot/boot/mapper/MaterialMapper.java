package com.springboot.boot.mapper;

import com.springboot.boot.pojo.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialMapper {

    String selectPrice(@Param("materialName")String materialName);

    List<Material> selectMaterial(@Param("materialName")String materialName);
}