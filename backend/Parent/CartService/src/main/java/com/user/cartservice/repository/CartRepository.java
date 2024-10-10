package com.user.cartservice.repository;

import com.user.cartservice.model.Cart;
import com.user.cartservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository  extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserId(Integer userId);
}
