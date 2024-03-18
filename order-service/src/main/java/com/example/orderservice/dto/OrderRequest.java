package com.example.orderservice.dto;


import com.example.orderservice.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 *  OrderRequest 是一个List类型
 *  多个商品信息
 */
public class OrderRequest {

    private List<OrderItemDto> orderItemDtoList;
}
