package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data //生成所有字段的getter、toString()、hashCode()、equals()、所有非final字段的setter、构造器
@NoArgsConstructor
@AllArgsConstructor
@Builder // 通过@Builder注解，lombok还可以方便的实现建造者模式。
public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
