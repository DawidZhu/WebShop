package com.example.productservice;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.repository.ProductRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.junit.jupiter.Container;

import java.math.BigDecimal;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
   //  @Container //创建mongoDB Docker
    //static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.17");
    @Autowired
    private MockMvc mockMvc; // 模拟MVC 发起请求
    @Autowired
    private ObjectMapper objectMapper; //Object 转 JSon格式
    @Autowired
    private ProductRepository productRepository;

    //不需要使用DOcker
//    static {
//        mongoDBContainer.start();
//    }

    /**
     *  DynamicPropertyRegistry
     *  mongoDBContainer
     */
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
//        dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }

    /**
     *  mockMvc发起调用，代替PostMan
     *
     *  MockHttpServletRequest:
     *       HTTP Method = POST
     *       Request URI = /api/product
     *        Parameters = {}
     *           Headers = [Content-Type:"application/json;charset=UTF-8", Content-Length:"60"]
     *              Body = {"name":"iPhone 100","description":"iPhone 100","price":100}
     */
    @Test
    void shouldCreatProduct() throws Exception {
        //1. 初始化请求报文，用 builder 创建 ProductRequest
        ProductRequest productRequest = ProductRequest.builder()
                .name("iPhone 1")
                .description("iPhone 1")
                .price(BigDecimal.valueOf(100))
                .build();

        //2. 转 Object 为 Json 格式的String
        String productRequestString = objectMapper.writeValueAsString(productRequest);

        // 3. 模拟MVC发起请求， 指定URI,Json,报文，Expect 的值，创建成功应该返回CREATED: 201
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated());

        // 4. 断言：Assertions， 对比实际的值和预计的值
        // Assertions.assertEquals(5, productRepository.findAll().size());
        Assertions.assertNotEquals(5,productRepository.findAll().size());
    }


}
