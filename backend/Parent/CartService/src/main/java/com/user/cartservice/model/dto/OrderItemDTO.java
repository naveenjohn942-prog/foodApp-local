package com.user.cartservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class OrderItemDTO {
    private Long itemId;
    private int quantity;
    private double price;
}
