package com.springboot.boot.service;

import com.springboot.boot.pojo.Inventory;
import com.springboot.boot.pojo.Material;

import java.util.List;

public interface InventoryService {
    int addInventory(Material material);
    List<Inventory> checkInventory(Inventory inventory);
    int updateInventoryNum(Inventory inventory);
    List<Inventory> selectInventory(int status);
    int updateStatus(Inventory inventory);
}
