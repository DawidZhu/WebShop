package com.example.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@ToString
public class InventoryResponse {

    private Long id;
    private String name;
    private boolean isInStock;

}
