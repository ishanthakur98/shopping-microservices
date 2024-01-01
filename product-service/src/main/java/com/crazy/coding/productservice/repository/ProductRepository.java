package com.crazy.coding.productservice.repository;


import com.crazy.coding.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product , String> {
}
