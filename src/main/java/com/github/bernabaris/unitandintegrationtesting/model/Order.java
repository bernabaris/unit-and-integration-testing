package com.github.bernabaris.unitandintegrationtesting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private String buyer;
    private Double price;
    private int qty;
}
