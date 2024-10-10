package com.user.cartservice.service;

import com.user.cartservice.model.Cart;
import com.user.cartservice.model.CartItem;
import com.user.cartservice.model.dto.CartDTO;

import java.util.List;

public interface CartService {
    void addItemToCart(Integer userId, Long itemId, int quantity);
    double calculateTotalPrice(List<CartItem> items);
    CartDTO viewCart(Integer userId);
    CartDTO checkout(Integer userId) throws Exception;
    CartDTO removeItemFromCart(Integer userId, Long itemId);
}
