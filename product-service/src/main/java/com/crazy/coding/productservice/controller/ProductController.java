package com.crazy.coding.productservice.controller;

import com.crazy.coding.productservice.model.Product;
import com.crazy.coding.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product){
        productRepository.save(product);
    }
}
