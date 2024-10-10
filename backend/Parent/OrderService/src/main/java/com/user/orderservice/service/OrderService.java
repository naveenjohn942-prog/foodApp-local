package com.user.orderservice.service;

import com.user.orderservice.model.Orders;
import com.user.orderservice.model.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    Orders createOrder(OrderDTO orderDTO);
    Orders getOrderById(Long id);
    List<Orders> getOrderHistory(Long userId);
    List<Orders> getOrderByStatus(Long userId, String status);
}
