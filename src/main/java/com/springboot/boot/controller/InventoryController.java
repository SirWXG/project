package com.springboot.boot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.boot.pojo.Inventory;
import com.springboot.boot.service.InventoryService;
import com.springboot.boot.utils.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "selectInventoryByStatus")
    @ResponseBody
    public SimpleResult getInventory(){
        SimpleResult result = new SimpleResult();
        List<Inventory> list = inventoryService.selectInventory(0);
        result.setCode(0);
        result.setMsg("");
        result.setCount(list.size());
        List<Inventory> lists = inventoryService.selectInventory(0);
        result.setData(lists);
        return result;
    }

    @RequestMapping(value = "/updateInventory",method = RequestMethod.POST)
    @ResponseBody
    public SimpleResult updateInventory(Inventory inventory){
        int flag = inventoryService.updateStatus(inventory);
        return SimpleResult.getSimple(flag<1?0:1,"");
    }

    @RequestMapping(value = "/selectInventory",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Inventory> selectInventory(@RequestParam(name = "page",defaultValue = "1")Integer page){
        PageHelper.startPage(page,10);
        List<Inventory> list = inventoryService.selectInventory(1);
        PageInfo<Inventory> info = new PageInfo<>(list,4);
        return info;
    }

}
