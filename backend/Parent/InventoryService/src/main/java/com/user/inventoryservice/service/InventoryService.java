package com.user.inventoryservice.service;

import com.user.inventoryservice.model.InventoryItem;

import java.util.List;

public interface InventoryService {
    List<InventoryItem> getAllItems();
    InventoryItem getItemById(Long id);
    List<InventoryItem> getItemsByCategory(String category);
    InventoryItem createItem(InventoryItem item);
    void updateStock(Long id, int stock,double price);
    boolean checkStock(Long itemId, int quantity);
    void deductStock(Long itemId, int quantity);

}
