package com.user.cartservice.service;

import com.user.cartservice.model.InventoryItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

@FeignClient(name = "InventoryService", url = "http://localhost:8082")
public interface InventoryServiceFeignClient {
    @GetMapping("/inventory/items/{id}")
    ResponseEntity<InventoryItemDTO> getItemById(@PathVariable("id") Long id);
    @PutMapping("/inventory/items/deductStock")
    ResponseEntity<String> deductStock(@RequestParam("itemId") Long itemId, @RequestParam("quantity") int quantity);
    @GetMapping("/inventory/items/checkStock")
    ResponseEntity<Boolean> checkStock(@RequestParam Long itemId, @RequestParam int quantity);
}
