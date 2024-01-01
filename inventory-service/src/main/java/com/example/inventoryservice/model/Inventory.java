package com.example.inventoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "Inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "SKU_CODE")
    private String skuCode;

    private Integer stock;

}
