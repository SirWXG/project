package com.springboot.boot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.pojo.Material;
import com.springboot.boot.pojo.User;
import com.springboot.boot.service.InventoryService;
import com.springboot.boot.service.MaterialService;
import com.springboot.boot.utils.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private InventoryService inventoryService;

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
    @ResponseBody
    public PageInfo<Material> selectMaterialByProject(@RequestParam(name = "page",defaultValue = "1")Integer page,
            @RequestParam(name = "projectVar",defaultValue = "")String projectVar,HttpSession session){
        String id = (String) session.getAttribute("projectId");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("materialVar",projectVar);
        map.put("projectName",id);
        PageHelper.startPage(page,10);
        List<Material> list =  materialService.selectMaterialByProject(map);
        PageInfo<Material> info = new PageInfo<>(list,4);
        return info;
    }

    @RequestMapping(value = "/addMaterial",method = RequestMethod.POST)
    @ResponseBody
    public SimpleResult addMaterial(Material material,HttpSession session){
        String projectName= (String)session.getAttribute("projectName");
        material.setProjectName(projectName);
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
                                       @RequestParam(name = "status",defaultValue = "0")Integer status,HttpSession session){
        SimpleResult result = new SimpleResult();
        Material material = new Material();
        material.setId(materialId);
        material.setIsFailer(isFailer);
        User user =(User)session.getAttribute("user");
        String rolerName = user.getRolerName();
        if("材料主管".equals(rolerName)){
            status=1;
        }else if("经理".equals(rolerName)){
            status=2;
            List<Material> list = materialService.selectMaterialById(materialId);
            inventoryService.addInventory(list.get(0));
        }
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

    @RequestMapping(value = "/setSession",method = RequestMethod.POST)
    @ResponseBody
    public String setSession(@RequestParam(name = "id")String id, HttpSession session){
        session.setAttribute("projectId",id);
        return "";
    }

    @RequestMapping(value = "/updateMaterialNum",method = RequestMethod.POST)
    @ResponseBody
    public SimpleResult updateMaterialNum(Material material){
        SimpleResult result = new SimpleResult();
        int flag = materialService.updateMaterialNum(material);
        if(flag<1){
            result.setMsg("修改失败");
        }else{
            result.setCode(1);
        }
        return result;
    }

    @RequestMapping(value = "/selectMaterialByResult",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Material> selectMaterialByResult(@RequestParam(name="status",defaultValue = "0")Integer status,
                                               @RequestParam(name="page",defaultValue = "1")Integer page){
        PageHelper.startPage(page,10);
        List<Material> list = materialService.selectMaterialForStatus();
        PageInfo<Material> info = new PageInfo<>(list,4);
        return info;
    }
}
