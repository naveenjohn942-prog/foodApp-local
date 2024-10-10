package com.user.inventoryservice.service.Impl;

import com.user.inventoryservice.exception.ResourceNotFoundException;
import com.user.inventoryservice.model.InventoryItem;
import com.user.inventoryservice.repository.InventoryRepository;
import com.user.inventoryservice.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public InventoryItem getItemById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    @Override
    public List<InventoryItem> getItemsByCategory(String category) {
        return inventoryRepository.findByCategory(category);
    }

    @Override
    public InventoryItem createItem(InventoryItem item) {
        return inventoryRepository.save(item);
    }

    @Override
    public void updateStock(Long id, int stock,double price) {
        InventoryItem existingItem = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        existingItem.setStock(stock);
        existingItem.setPrice(price);
        inventoryRepository.save(existingItem);
    }

    @Override
    public boolean checkStock(Long itemId, int quantity) {
        InventoryItem item = inventoryRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));

        return item.getStock() >= quantity;
    }

    @Override
    public void deductStock(Long itemId, int quantity) {
        InventoryItem item = inventoryRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));

        if (item.getStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for item ID: " + itemId);
        }

        item.setStock(item.getStock() - quantity);
        inventoryRepository.save(item);
    }


}
