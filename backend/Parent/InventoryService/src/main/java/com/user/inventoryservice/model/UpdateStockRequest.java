package com.user.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStockRequest {
    private int stock;
    private double price;
    private String image;
}
