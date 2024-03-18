package com.example.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * MongoDB是一个文档数据库（以JSON 为数据模型），由C++语言编写，
 * 旨在为WEB应用提供可扩展的高性能数据存储解决方案。 文档来自于“JSON Document”，并非我们一般理解的PDF，WORD 文档。
 * MongoDB是非关系数据库当中功能最丰富，最像关系数据库的。
 * MongoDB使用集合（collection）和文档（document）来描述和存储数据，
 * 集合（collection）就相当于表，文档（document）相当于行，字段 (field) 相当于列，
 * 不像MySQL之类的关系型数据库，表结构是固定的，比如某一行由若干列组成，行行都一样，
 *
 * 文档是按BSON（JSON的轻量化二进制格式）存储的
 * 而MongoDB不同，一个集合里的多个文档可以有不同的结构，更灵活一些
 *
 * @ Document(value = "product")  //标注在实体类上，把java类声明为mongodb的文档，
 * 可以通过collection参数指定这个类对应的文档，类似于hibernate的entity注解，标明由mongo来维护该表
 */
@Data // 生成所有字段的getter、toString()、hashCode()、equals()、所有非final字段的setter、构造器
@AllArgsConstructor
@NoArgsConstructor
@Builder // 作用主要是用来生成对象，并且可以为对象链式赋值。
@Document(value = "product")
@ToString
// ToString 的作用？
public class Product {

    // todo mongoDB 应该存储非结构化数据，每个document 会有不同的 field？

    @Id //springboot data, 主键，不可重复，自带索引
    private String id;

    @Indexed(unique = true) // 测试使用，唯一索引
    private String name;

    private String description;
    private BigDecimal price;

}
