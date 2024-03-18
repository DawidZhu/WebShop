package com.example.orderservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

	// from product
	private String name;
	private BigDecimal price;

	// from inventory
	private Integer quantity;

}
