package com.user.cartservice.model.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemDTO {
    private Long productId;
    private Long itemId;
    private int quantity;
    private String itemName;
    private double price;
    private String imageUrl;
}
