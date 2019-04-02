package com.springboot.boot.service;


import com.springboot.boot.pojo.Material;
import java.util.List;

public interface MaterialService {

    String selectPrice(String materialName);

    List<Material> selectMaterial(String materialName);
}
