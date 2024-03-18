package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DAO (Data Access Object) and DTO (Data Transfer Object) patterns
 * are used in Object Relational Mapping.
 * DAO acts as a bridge between the database and the application.
 * DTO acts as a data store from where the data is received and transferred to
 * different layers i.e., to the DAO application
 *
 * 直接与service 业务层交互，对model 又做了隔离， 松耦合
 */
@Data //生成所有字段的getter、toString()、hashCode()、equals()、所有非final字段的setter、构造器
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
}
