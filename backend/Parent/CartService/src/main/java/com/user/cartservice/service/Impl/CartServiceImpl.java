package com.user.cartservice.service.Impl;

import com.user.cartservice.model.*;
import com.user.cartservice.model.dto.CartDTO;
import com.user.cartservice.model.dto.OrderItemDTO;
import com.user.cartservice.repository.CartRepository;
import com.user.cartservice.service.CartService;
import com.user.cartservice.service.InventoryServiceFeignClient;
import com.user.cartservice.service.OrderServiceFeignClient;
import com.user.cartservice.service.UserServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.user.cartservice.util.Mapping.convertToDTO;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserServiceFeignClient userServiceFeignClient;
    private final InventoryServiceFeignClient inventoryServiceFeignClient;
    @Autowired
    private OrderServiceFeignClient orderServiceFeignClient;
    public CartServiceImpl(CartRepository cartRepository,UserServiceFeignClient userServiceFeignClient,InventoryServiceFeignClient inventoryServiceFeignClient) {
        this.cartRepository = cartRepository;
        this.userServiceFeignClient = userServiceFeignClient;
        this.inventoryServiceFeignClient = inventoryServiceFeignClient;
    }
    @Override
    public void addItemToCart(Integer userId, Long itemId, int quantity) {
        ResponseEntity<UserDTO> userResponse = userServiceFeignClient.getUserById(userId);

        if (userResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("User not found");
        }

        // Fetch item
        ResponseEntity<InventoryItemDTO> itemResponse = inventoryServiceFeignClient.getItemById(itemId);
        if (itemResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Item not found");
        }

        InventoryItemDTO itemDTO = itemResponse.getBody();

        // Fetch or create cart
        Cart cart = cartRepository.findByUserIdOrderByIdAsc(userId).orElse(new Cart());
        cart.setUserId(userId);

        // Add or update item in cart
        List<CartItem> items = cart.getItems() != null ? cart.getItems() : new ArrayList<>();
        Optional<CartItem> existingItem = items.stream()
                .filter(cartItem -> cartItem.getItemId().equals(itemId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setItemId(itemDTO.getId());
            newItem.setProductId(itemDTO.getId());
            newItem.setUserId(Long.valueOf(userId));
            newItem.setItemName(itemDTO.getName());
            newItem.setPrice(itemDTO.getPrice());
            String imageUrl = itemDTO.getImageUrl().replace("http://localhost:8082", "https://inventory.autone.eu.org");
            newItem.setImageUrl(imageUrl);
            newItem.setQuantity(quantity);
            items.add(newItem);
        }

        cart.setItems(items);
        cart.setTotalPrice(calculateTotalPrice(items));

        // Save cart
        Cart savedCart = cartRepository.save(cart);

        // Convert to DTO
        convertToDTO(savedCart);
    }

    @Override
    public double calculateTotalPrice(List<CartItem> items) {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    @Override
    public CartDTO viewCart(Integer userId) {
        Optional<Cart> optionalCart = cartRepository.findByUserIdOrderByIdAsc(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            return convertToDTO(cart);
        } else {
            // Return an empty cart or handle appropriately
            return new CartDTO(userId, 0.0, List.<CartItem>of());
        }
    }

    @Override
    public CartDTO checkout(Integer userId) throws Exception {
        System.out.println("userId = " + userId);
        Optional<Cart> optionalCart = cartRepository.findByUserIdOrderByIdAsc(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<String> outOfStockItems = new ArrayList<>();

            for (CartItem item : cart.getItems()) {
                Long itemId = item.getItemId();
                int quantity = item.getQuantity();

                ResponseEntity<Boolean> response = inventoryServiceFeignClient.checkStock(itemId, quantity);
                if (response.getStatusCode() == HttpStatus.NOT_FOUND || !response.getBody()) {
                    outOfStockItems.add(item.getItemName());
                }
            }

            if (!outOfStockItems.isEmpty()) {
                throw new Exception("The following items are out of stock: " + String.join(", ", outOfStockItems));
            }

            for (CartItem item : cart.getItems()) {
                inventoryServiceFeignClient.deductStock(item.getItemId(), item.getQuantity());
            }

            // Prepare OrderDTO to create an order
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserId(userId.longValue());
            List<OrderItemDTO> orderItems = cart.getItems().stream()
                    .map(cartItem -> {
                        OrderItemDTO orderItemDTO = new OrderItemDTO();
                        orderItemDTO.setItemId(cartItem.getItemId());
                        orderItemDTO.setQuantity(cartItem.getQuantity());
                        orderItemDTO.setPrice(cartItem.getPrice());
                        return orderItemDTO;
                    }).collect(Collectors.toList());
            orderDTO.setOrderItems(orderItems);

            // Call the create order API
            orderServiceFeignClient.createOrder(orderDTO);

            // Clear cart after successful checkout
            cart.getItems().clear();
            Cart savedCart = cartRepository.save(cart);

            return convertToDTO(savedCart);
        } else {
            throw new IllegalStateException("Cart not found for user ID: " + userId);
        }
    }
    @Override
    public CartDTO removeItemFromCart(Integer userId, Long itemId, int quantity) {
        Optional<Cart> optionalCart = cartRepository.findByUserIdOrderByIdAsc(userId);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<CartItem> items = cart.getItems();

            Optional<CartItem> optionalItem = items.stream()
                    .filter(item -> item.getItemId().equals(itemId))
                    .findFirst();

            if (optionalItem.isPresent()) {
                CartItem cartItem = optionalItem.get();

                if (cartItem.getQuantity() <= quantity) {
                    items.remove(cartItem);
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() - quantity);
                }

                cart.setTotalPrice(calculateTotalPrice(items));

                Cart savedCart = cartRepository.save(cart);
                return convertToDTO(savedCart);
            } else {
                throw new IllegalStateException("Item not found in cart for user ID: " + userId);
            }
        } else {
            throw new IllegalStateException("Cart not found for user ID: " + userId);
        }
    }

}
