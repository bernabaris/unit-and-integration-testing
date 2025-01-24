package com.github.bernabaris.unitandintegrationtesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String buyer;
    private Double price;
    private int qty;
}
