package com.springboot.boot.controller;

import com.springboot.boot.pojo.Material;
import com.springboot.boot.service.MaterialService;
import com.springboot.boot.utils.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @RequestMapping(value = "/selectMaterialPrice",method = RequestMethod.POST)
    @ResponseBody
    public String getMaterialPrice(@RequestParam(name = "materialName")String materialName){
        return materialService.selectPrice(materialName);
    }

    @RequestMapping(value = "/selectMaterial",method = RequestMethod.GET)
    @ResponseBody
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

    @RequestMapping(value = "/selectMaterialByProject",method = RequestMethod.GET)
    public String selectMaterialByProject(@RequestParam(name = "projectId")String projectId, Model model){
        List<Material> list =  materialService.selectMaterialByProject(projectId);
        model.addAttribute("material",list);
        return "material_list";
    }

    @RequestMapping(value = "/addMaterial",method = RequestMethod.POST)
    @ResponseBody
    public SimpleResult addMaterial(Material material){
        int flag = materialService.addMaterial(material);
        return SimpleResult.getSimple(flag<1?0:1,flag<1?"申请失败":"申请成功");
    }

    @RequestMapping(value = "/selectMaterialByStatus",method = RequestMethod.GET)
    @ResponseBody
    public SimpleResult selectMaterialByStatus(){
        SimpleResult result = new SimpleResult();
        List<Material> list = materialService.selectMaterialByStatus();
        result.setCount(list.size());
        List<Material> lists = materialService.selectMaterialByStatus();
        result.setMsg("");
        result.setCode(0);
        result.setData(lists);
        return result;
    }

    @RequestMapping(value = "/updateMaterial",method = RequestMethod.POST)
    @ResponseBody
    public SimpleResult updateMaterial(@RequestParam(name = "materialId")Integer materialId,
                                       @RequestParam(name = "isFailer",defaultValue = "")String isFailer,
                                       @RequestParam(name = "status")Integer status){
        SimpleResult result = new SimpleResult();
        Material material = new Material();
        material.setId(materialId);
        material.setIsFailer(isFailer);
        material.setStatus(status);
        int flag = materialService.updateMaterial(material);
        if(flag<1){
            result.setCode(0);
            result.setMsg("审批失败,请重新审批");
        }else{
            result.setCode(1);
        }
        return  result;
    }
}
