package com.example.productservice.repository;

import com.example.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 * Mongodb 命令：
 * 连接数据库：mongo；或者 mongo 127.0.0.1/test
 * 查看库：show dbs;
 * 使用或创建某个库：use product-service
 * 显示当前数据库：db; 或者 db.getName();
 * 参看表： show collections; show table;
 * 创建collection名为Hello :  db.createCollection("hello");
 * db.createCollection(name, {capped: <Boolean>, autoIndexId: <Boolean>, size: <number>, max <number>} )
 * 删除数据库：db.dropDatabase();
 * 显示当前collection的状态信息： db.myCollection.stats();
 * 显示当前数据库的状态： db.stats();
 *
 * 删除当前collection ：db.myCollection.drop();
 * 注意：向集合中插入文档时，如果集合不存在，则会自动创建集合;
 * 文档的数据结构和 JSON 基本一样。
 * 所有存储在集合中的数据都是 BSON 格式。
 * db.T_test.insert({name:'david', age:30, phone:'123456',tags:['A','B']});
 * 查collection的所有值：db.T_test.find();
 * db.T_test.find().pretty(); pretty() 方法以格式化的方式来显示所有文档
 * db.T_test.find().pretty()
 * db.collection.insertMany() 用于向集合插入一个多个文档
 *
 * 1. 我们也可以将数据定义为一个变量，如下所示：
 * > document=({title: 'MongoDB 教程',
 *     description: 'MongoDB 是一个 Nosql 数据库',
 *     by: '菜鸟教程',
 *     url: 'http://www.runoob.com',
 *     tags: ['mongodb', 'database', 'NoSQL'],
 *     likes: 100
 * });
 *
 * 2. 执行插入操作：
 * > db.T_test.insert(document)
 * WriteResult({ "nInserted" : 1 })
 * >
 *
 * 创建document: db.COLLECTION_NAME.insert(document)
 *
 * MongoDB 查询文档使用 find() 方法。
 * find() 方法以非结构化的方式来显示所有文档。
 * db.collection.find(query, projection)
 * query ：可选，使用查询操作符指定查询条件
 * projection ：可选，使用投影操作符指定返回的键。
 * db.T_test.find({name:'david'},{age:1}).pretty(): 结果只显示age字段
 * db.T_test.find({name:'david'}).pretty()
 * 名字不等于 $ne; gte 数字大于等于， lt:小于
 * db.T_test.find({name:{$ne:"Dawei Zhu"}}).pretty();
 *
 * MongoDB 更新文档 update()
 * db.collection.update(
 *    <query>,
 *    <update>
 * )
 * # query：修改的查询条件，类似于SQL中的WHERE部分
 * # update：更新属性的操作符，类似与SQL中的SET部分
 * db.T_test.update({name:'david'},{$set:{name:'Dawei Zhu'}})
 *
 * 删除 文档deleteOne(), deleteMany()
 * db.T_test.deleteOne({"title" : "MongoDB 教程"});
 *
 *
 * 集合（collection）就相当于表，文档（document）相当于行，字段 (field) 相当于列，
 *  JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 */
public interface ProductRepository extends MongoRepository<Product, String> {
    //JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作

    /**
     * 方法命名规则查询就是根据方法的名字，就能创建查询。
     * 只需要按照Spring Data JPA提供的方法命名规则定义方法的名称，就可以完成查询工作。
     * Spring Data JPA在程序执行的时候会根据方法名称进行解析，并自动生成查询语句进行查询
     *
     * Generated Query Methods
     * the more common type of query that Spring Data usually provides,
     * auto-generated queries out of method names.
     *
     * The only thing we need to do to leverage these kinds of queries is to
     * declare the method on the repository interface:
     */

    //@Query 注解的使用非常简单，只需在方法上面标注该注解，同时提供一个JPQL查询语句即可
    @Query("{ 'name' : ?0 }")
    //The placeholder ?0 references the first parameter of the method.
    Optional<Product> findProductByName(String name);
}
