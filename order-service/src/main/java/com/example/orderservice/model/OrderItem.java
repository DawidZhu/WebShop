package com.example.orderservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @ Table（可选）
 * name属性：指定表名，不指定时默认按驼峰命名法拆分将类名，并以下划线连接
 *
 *
 */
@Entity
@Table(name = "order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String orderNumber;

    // from product
    private String name;
    private BigDecimal price;

    // from inventory
    private Integer quantity;
    private String address;

}
