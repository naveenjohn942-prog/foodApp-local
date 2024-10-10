package com.user.orderservice.controller;

import com.user.orderservice.model.Orders;
import com.user.orderservice.model.dto.OrderDTO;
import com.user.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderDTO orderDTO) {
        Orders orders = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
        Orders orders = orderService.getOrderById(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Orders>> getOrderHistory(@RequestParam Long userId) {
        List<Orders> orders = orderService.getOrderHistory(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Orders>> getOrderByStatus(@RequestParam Long userId, @RequestParam String status) {
        List<Orders> orders = orderService.getOrderByStatus(userId, status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
