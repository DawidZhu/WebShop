package com.example.orderservice.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * JPA
 * 包含 List 集合的类
 *
 * 这个应该用 MongoDB？
 *
 * @Entity 是 JPA 中的注解，用于将 Java 类映射到数据库表。在这个例子中，Order 类被标记为实体类，表示它将与数据库中的表进行映射。
 *
 * @Table 注解用于指定实体类与数据库表之间的映射关系。
 *
 * @Id 注解用于指定实体类中用作主键的属性。在这个例子中，id 属性被指定为主键。
 *
 * @OneToMany(cascade = CascadeType.ALL) 注解用于指定订单与订单项之间的一对多关系，同时配置级联操作，表示当对订单进行操作时，将级联操作到关联的订单项。
 *
 * orderItemsList：这是一个一对多关系的字段，表示订单和订单项之间的关联关系。它可能不是直接在订单表中存储的字段，而是通过外键关联到另一个表中
 */

@Entity
@Table(name = "t_order")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String username;
    private String orderNumber;
    private Timestamp creatTime;

    //cascade属性表明操作是否从父对象级联到被关联的对象
    //它可能不是直接在订单表中存储的字段，而是通过外键关联到另一个表中
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItemsList;
}
