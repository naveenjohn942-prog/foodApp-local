package com.user.inventoryservice.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemCreateDTO {
    private String name;
    private String category;
    private int stock;
    private double price;
    private String image;

}
