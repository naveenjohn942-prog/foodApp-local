package com.user.cartservice.service;

import com.user.cartservice.model.Orders;
import com.user.cartservice.model.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "OrderService", url = "http://localhost:8086/orders")
public interface OrderServiceFeignClient {
    @PostMapping("/create")
    ResponseEntity<Orders> createOrder(@RequestBody OrderDTO orderDTO);
}
