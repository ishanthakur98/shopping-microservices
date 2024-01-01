package com.crazy.coding.orderservice.repository;

import com.crazy.coding.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order , Long> {
}
