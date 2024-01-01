package com.crazy.coding.orderservice.controller;

import com.crazy.coding.orderservice.client.InventoryClient;
import com.crazy.coding.orderservice.dto.OrderDto;
import com.crazy.coding.orderservice.model.Order;
import com.crazy.coding.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;

    private final StreamBridge streamBridge;

    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto){
        CircuitBreaker circuitBreaker =  circuitBreakerFactory.create("inventory");
        Supplier<Boolean> booleanSupplier = () -> orderDto.getItemsList().stream()
                .allMatch(orderLineItems -> inventoryClient.checkStock(orderLineItems.getSkuCode()));
        boolean allProductInStock = circuitBreaker.run(booleanSupplier , throwable -> handleErrorCase());
        if(allProductInStock){
            Order order = new Order();
            order.setOrderLineItems(orderDto.getItemsList());
            order.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(order);
            log.info("sending order details to notification service {}",order.getOrderNumber());
            streamBridge.send("notificationEventSupplier-out-0",order.getId());
            return "Order placed successfully";
        }else{
            return "Order failed. One of product is not available";
        }



    }

    private boolean handleErrorCase(){
        return false;
    }
}
