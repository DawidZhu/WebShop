package com.example.orderservice.dto;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderResponse that = (OrderResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
            && Objects.equals(price, that.price) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity);
    }
}
