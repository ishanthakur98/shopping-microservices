package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    @GetMapping("/{skuCode}")
    Boolean isInStock(@PathVariable String skuCode) {
        log.info("Checking in inventory for order with skuCode {}",skuCode);
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(()-> new RuntimeException("Cannot Find Product By sku code => "+skuCode));
        return inventory.getStock() > 0;
    }
}
