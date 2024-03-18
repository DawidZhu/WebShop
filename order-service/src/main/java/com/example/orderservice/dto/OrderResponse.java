package com.example.orderservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTOs normally are created as POJOs
 * DTO stands for Data Transfer Object, which is a design pattern.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;

    // private Long orderId;

	// from product
    private String name;
    private BigDecimal price;

    // from inventory
    private Integer quantity;
}
