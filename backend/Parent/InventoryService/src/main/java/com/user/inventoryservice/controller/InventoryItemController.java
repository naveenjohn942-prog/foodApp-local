package com.user.inventoryservice.controller;

import com.user.inventoryservice.model.InventoryItem;
import com.user.inventoryservice.model.UpdateStockRequest;
import com.user.inventoryservice.model.dto.InventoryItemCreateDTO;
import com.user.inventoryservice.model.dto.InventoryItemDTO;
import com.user.inventoryservice.service.Impl.Utility;
import com.user.inventoryservice.service.InventoryService;
import com.user.inventoryservice.util.InventoryItemMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
//@CrossOrigin(origins = "http://localhost:3000")
public class InventoryItemController {
    private final InventoryService inventoryService;

    public InventoryItemController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/items")
    public ResponseEntity<String> createItem(@ModelAttribute InventoryItemCreateDTO inventoryItemCreateDTO,
                                             @RequestParam("file") MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("File is missing or empty.");
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isEmpty()) {
                throw new IllegalArgumentException("The file name is missing or empty.");
            }

            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            InventoryItem inventoryItem = InventoryItemMapper.toEntity(inventoryItemCreateDTO);
            inventoryItem.setImage(uniqueFileName); // Store the unique file name in the inventory item

            String filePath = Utility.Upload_Path + File.separator + uniqueFileName;

            File directory = new File(Utility.Upload_Path);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Files.copy(file.getInputStream(), Paths.get(filePath));

            inventoryService.createItem(inventoryItem);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Item " + inventoryItem.getName() + " created successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image or save item: " + e.getMessage());
        }
    }

    @GetMapping("/items")
    public ResponseEntity<List<InventoryItemDTO>> getAllItems() {
        List<InventoryItem> items = inventoryService.getAllItems();
        List<InventoryItemDTO> itemDTOs = items.stream()
                .map(item -> {
                    InventoryItemDTO dto = InventoryItemMapper.toDTO(item);
                    dto.setImageUrl("http://localhost:8082/inventory/items/image/" + item.getImage());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(itemDTOs, HttpStatus.OK);
    }

    @GetMapping("/items/image/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            String filePath = Utility.Upload_Path + File.separator + filename;
            System.out.println("Trying to access file: " + filePath);  // Log the file path
            Path imagePath = Paths.get(filePath);
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/items/category/{category}")
    public ResponseEntity<List<InventoryItemDTO>> getItemsByCategory(@PathVariable String category) {
        List<InventoryItem> items = inventoryService.getItemsByCategory(category);
        List<InventoryItemDTO> itemDTOs = items.stream()
                .map(item -> {
                    InventoryItemDTO dto = InventoryItemMapper.toDTO(item);
                    dto.setImageUrl("http://localhost:8082/inventory/items/image/" + item.getImage());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(itemDTOs, HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<InventoryItemDTO> getItemById(@PathVariable Long id) {
        InventoryItem item = inventoryService.getItemById(id);
        return new ResponseEntity<>(InventoryItemMapper.toDTO(item), HttpStatus.OK);
    }

    @PutMapping("/updateStock/{id}")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestBody UpdateStockRequest updateStockRequest) {
        inventoryService.updateStock(id, updateStockRequest.getStock(),updateStockRequest.getPrice());
        return new ResponseEntity<>("Stock updated successfully", HttpStatus.OK);
    }

    @GetMapping("/items/checkStock")
    public ResponseEntity<Boolean> checkStock(@RequestParam Long itemId, @RequestParam int quantity) {
        boolean isStockAvailable = inventoryService.checkStock(itemId, quantity);
        if (isStockAvailable) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/items/deductStock")
    public ResponseEntity<String> deductStock(@RequestParam Long itemId, @RequestParam int quantity) {
        try {
            inventoryService.deductStock(itemId, quantity);
            return new ResponseEntity<>("Stock deducted successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to deduct stock: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
