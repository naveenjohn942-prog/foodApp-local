package com.user.cartservice.controller;

import com.user.cartservice.model.dto.CartDTO;
import com.user.cartservice.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addItemToCart(@RequestParam Integer userId,@RequestParam Long itemId,@RequestParam int quantity, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        cartService.addItemToCart(userId, itemId, quantity);
        return new ResponseEntity<>("Item added successfully", HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<CartDTO> viewCart(@RequestParam Integer userId, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header in CartService: " + authorizationHeader);

        CartDTO cartDTO = cartService.viewCart(userId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestParam Integer userId, HttpServletRequest request) throws Exception {
        cartService.checkout(userId);
        return new ResponseEntity<>("Checkout successful", HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CartDTO> removeItemFromCart(@RequestParam Integer userId,@RequestParam Long itemId) {
        CartDTO cartDTO = cartService.removeItemFromCart(userId, itemId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }
}
