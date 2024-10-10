package com.user.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "InventoryService")
public interface InventoryClient {
    @GetMapping("/inventory/items/checkStock")
    ResponseEntity<Boolean> checkStock(@RequestParam Long itemId, @RequestParam int quantity);

    @PutMapping("/inventory/items/deductStock")
    ResponseEntity<String> deductStock(@RequestParam Long itemId, @RequestParam int quantity);
}
