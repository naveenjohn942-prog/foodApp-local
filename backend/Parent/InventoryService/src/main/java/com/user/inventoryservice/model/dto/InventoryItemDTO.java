package com.user.inventoryservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class InventoryItemDTO {
    private Long id;
    private String name;
    private String category;
    private int stock;
    private double price;
    private String imageUrl;

    public InventoryItemDTO(Long productId, String name, String category, int stock, double price, String image, String imageUrl) {
        this.id = productId;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
