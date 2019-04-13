package com.springboot.boot.mapper;

import com.springboot.boot.pojo.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InventoryMapper {

    int addInventory(Inventory inventory);

    List<Inventory> checkInventory(Inventory inventory);

    int updateInventoryNum(Inventory inventory);

    List<Inventory> selectInventory(@Param("status")int status);

    int updateStatus(Inventory inventory);
}
