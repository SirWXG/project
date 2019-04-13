package com.springboot.boot.service.impl;

import com.springboot.boot.mapper.InventoryMapper;
import com.springboot.boot.pojo.Inventory;
import com.springboot.boot.pojo.Material;
import com.springboot.boot.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public int addInventory(Material material) {
        Inventory inventory = new Inventory();
        inventory.setAcceptTime(new Date());
        inventory.setMaterialName(material.getMaterialName());
        inventory.setMaterialNum(material.getMaterialNum());
        if(material.getBuyPrice() != null && !material.getBuyPrice().equals("") && !material.getBuyPrice().equals("null")){
            inventory.setMaterialPrice(Double.parseDouble(String.valueOf(material.getBuyPrice())));
            inventory.setTotalPrice(Double.parseDouble(String.valueOf(material.getBuyPrice()))*material.getMaterialNum());
        }
        inventory.setMaterialVar(material.getMaterialVar());
        inventory.setMaterialSupplier(material.getMaterialSupplier());
        inventory.setMaterialUnit(material.getMaterialUnit());
        inventory.setNote(material.getNote());
        inventory.setStatus(0);
        inventory.setMaterialSpec(material.getMaterialType());
        List<Inventory> list = checkInventory(inventory);
        if(list.size()<1){
            return inventoryMapper.addInventory(inventory);
        }else{
            return inventoryMapper.updateInventoryNum(inventory);
        }
    }

    @Override
    public List<Inventory> checkInventory(Inventory inventory) {
        return inventoryMapper.checkInventory(inventory);
    }

    @Override
    public int updateInventoryNum(Inventory inventory) {
        return inventoryMapper.updateInventoryNum(inventory);
    }

    @Override
    public List<Inventory> selectInventory(int status) {
        return inventoryMapper.selectInventory(status);
    }

    @Override
    public int updateStatus(Inventory inventory) {
        return inventoryMapper.updateStatus(inventory);
    }
}
