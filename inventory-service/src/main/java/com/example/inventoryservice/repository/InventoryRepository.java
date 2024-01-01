package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface InventoryRepository extends JpaRepository<Inventory,Long> {

//    @Query("select i from Inventory i where i.skuCode = :skuCode")
//    @Query(value = "SELECT * FROM Inventory  WHERE sku_code=:skuCode", nativeQuery = true)
    Optional<Inventory> findBySkuCode(String skuCode);

    @Query(value = "select * from Inventory",nativeQuery = true)
    List<Inventory> findEvery(String skuCode);
}
