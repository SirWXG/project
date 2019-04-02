package com.springboot.boot.controller;

import com.springboot.boot.pojo.Material;
import com.springboot.boot.service.MaterialService;
import com.springboot.boot.utils.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping(value = "/selectMaterialPrice")
    public String getMaterialPrice(@RequestParam(name = "materialName")String materialName){
        return materialService.selectPrice(materialName);
    }

    @GetMapping(value = "/selectMaterial")
    public SimpleResult getMaterial(@RequestParam(name = "materialName")String materialName){
        SimpleResult result = new SimpleResult();
        List<Material> list = materialService.selectMaterial(materialName);
        result.setCount(list.size());
        List<Material> lists= materialService.selectMaterial(materialName);
        result.setData(lists);
        result.setMsg("");
        result.setCode(0);
        return result;
    }
}
