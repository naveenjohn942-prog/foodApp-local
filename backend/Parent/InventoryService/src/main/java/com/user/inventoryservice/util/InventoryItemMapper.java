package com.user.inventoryservice.util;

import com.user.inventoryservice.model.InventoryItem;
import com.user.inventoryservice.model.dto.InventoryItemCreateDTO;
import com.user.inventoryservice.model.dto.InventoryItemDTO;

public class InventoryItemMapper {
    public static InventoryItemDTO toDTO(InventoryItem inventoryItem) {
        String imageUrl = "http://localhost:8082/inventory/items/image/" + inventoryItem.getImage();
        return new InventoryItemDTO(
                inventoryItem.getProductId(),
                inventoryItem.getName(),
                inventoryItem.getCategory(),
                inventoryItem.getStock(),
                inventoryItem.getPrice(),
                inventoryItem.getImage(),
                imageUrl
        );
    }

    public static InventoryItem toEntity(InventoryItemCreateDTO inventoryItemCreateDTO) {
        return new InventoryItem(
                null,
                inventoryItemCreateDTO.getName(),
                inventoryItemCreateDTO.getCategory(),
                inventoryItemCreateDTO.getStock(),
                inventoryItemCreateDTO.getPrice(),
                inventoryItemCreateDTO.getImage()
        );
    }
}
