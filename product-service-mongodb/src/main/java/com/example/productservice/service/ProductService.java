package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //创建带参数的构造器
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    /**
     * Builder 设计模式创建 对象
     */
    public void createProduct(ProductRequest productRequest){

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());

    }

    public List<ProductResponse> getAllProducts(){

        List<Product> products = productRepository.findAll();

        // product to ProductResponse
       return products.stream()
               .map(product -> mapToProductResponse(product))
               .collect(Collectors.toList());
    }

    /**
     * Product 对象转换成 ProductResponse
     */
    private ProductResponse mapToProductResponse(Product product) {

        // @Builder注释为你的类生成相对略微复杂的构建器API。
        // @Builder可以让你以下面显示的那样类似于链式的调用你的代码

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    /**
     * ifPresentOrElse 方法
     *
     */
    public Optional<Product> findProductById(String id){

        return productRepository.findById(id);

//        productRepository.findProductByName(name)
//                // Optional, 相当于 if != null , else 语句
//                .ifPresentOrElse(product -> log.info(" {} already exist!", product.toString() ),
//                        ()-> log.info("Don't Exist , Insert....") );

    }

    public Optional<Product> findProductByName(String name){

        return productRepository.findProductByName(name);
    }
}
