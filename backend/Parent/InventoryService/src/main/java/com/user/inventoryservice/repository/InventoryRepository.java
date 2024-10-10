package com.user.inventoryservice.repository;

import com.user.inventoryservice.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem,Long> {
    List<InventoryItem> findByCategory(String category);
}
