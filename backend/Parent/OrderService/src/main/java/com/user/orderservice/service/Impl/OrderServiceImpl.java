package com.user.orderservice.service.Impl;

import com.user.orderservice.model.Orders;
import com.user.orderservice.model.OrderItem;
import com.user.orderservice.model.dto.OrderDTO;
import com.user.orderservice.repository.OrderRepository;
import com.user.orderservice.service.InventoryClient;
import com.user.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    public OrderServiceImpl(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    @Override
    public Orders createOrder(OrderDTO orderDTO) {
        Orders orders = new Orders();
        orders.setUserId(orderDTO.getUserId());
        orders.setStatus("PENDING");
        orders.setTrackingId(UUID.randomUUID().toString());
        orders.setCreatedAt(LocalDateTime.now());

        // Map order items
        List<OrderItem> orderItems = orderDTO.getOrderItems().stream().map(dto -> {
            OrderItem item = new OrderItem();
            item.setItemId(dto.getItemId());
            item.setQuantity(dto.getQuantity());
            item.setPrice(dto.getPrice());
            return item;
        }).collect(Collectors.toList());

        for (OrderItem orderItem : orderItems) {
            ResponseEntity<Boolean> isStockAvailable = inventoryClient.checkStock(orderItem.getItemId(), orderItem.getQuantity());
            Boolean stockAvailable = isStockAvailable.getBody();
            if (stockAvailable == null || !stockAvailable) {
                throw new IllegalStateException("Stock unavailable for item ID: " + orderItem.getItemId());
            }
            // Deduct stock
            inventoryClient.deductStock(orderItem.getItemId(), orderItem.getQuantity());
        }

        orders.setOrderItems(orderItems);

        return orderRepository.save(orders);
    }


    @Override
    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public List<Orders> getOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Orders> getOrderByStatus(Long userId, String status) {
        return orderRepository.findByUserIdAndStatus(userId, status);
    }
}
