package com.user.cartservice.util;

import com.user.cartservice.model.Cart;
import com.user.cartservice.model.dto.CartDTO;
import com.user.cartservice.model.dto.CartItemDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Mapping {
    public static CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(cart.getUserId());
        cartDTO.setTotalPrice(cart.getTotalPrice());

        List<CartItemDTO> itemDTOs = cart.getItems().stream().map(item -> {
            CartItemDTO dto = new CartItemDTO();
            dto.setItemId(item.getItemId());
            dto.setProductId(item.getProductId());
            dto.setItemName(item.getItemName());
            dto.setPrice(item.getPrice());
            dto.setQuantity(item.getQuantity());
            return dto;
        }).collect(Collectors.toList());

        cartDTO.setItems(itemDTOs);
        return cartDTO;
    }
}
