package com.user.cartservice.model.dto;

import com.user.cartservice.model.CartItem;
import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class CartDTO {
    private Integer userId;
    private List<CartItemDTO> items;
    private double totalPrice;

    public CartDTO(Integer userId, double totalPrice, List<CartItem> items) {
        this.userId = userId;
        this.totalPrice = totalPrice;

        // Map CartItem objects to CartItemDTO objects
        this.items = items.stream()
                .map(item -> new CartItemDTO(item.getProductId(), item.getItemId(), item.getQuantity(), item.getItemName(), item.getPrice(),item.getImageUrl()))
                .toList();
    }
}
