package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * JPQL（Java Persistence Query Language）：
 * JPQL 是一种基于对象的查询语言，用于执行对实体对象的查询操作。
 * 与 SQL 类似，JPQL 允许您执行诸如 SELECT、INSERT、UPDATE 和 DELETE 等操作，但它是面向对象的，而不是面向表的。
 *
 *
 * JPA 支持事务管理，确保对数据库的所有操作都处于一个原子性的事务中，要么全部成功执行，要么全部回滚。
 * Declarative transaction management
 * 可以使用 @Transactional 注解来声明一个方法或类需要在事务管理下运行。
 *
 * save 方法可以用于保存或更新实体对象，如果实体对象的主键已经存在，则执行更新操作，否则执行保存操作。
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    // 自定义的方法，不需要实现. 因为JPA根据方法名称实现了各种By方法
    // Optional<Inventory> findBySkuCode();

    // Optional<Inventory> findById(String skuCode);

    /**
     * 在 JPA (Java Persistence API) 中，IN 关键字用于构建查询条件，允许您在查询中使用一个集合作为参数，以匹配指定字段的值是否在该集合中。使用 IN 关键字可以方便地执行类似于 SQL 中的 IN 子句的查询操作。
     */
    List<Inventory> findByName(String name);



}
