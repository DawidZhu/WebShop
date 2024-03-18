package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * http://localhost:8081/actuator
 *
 *  CompletableFuture用于异步编程 asynchronous，
 *  异步编程是编写非阻塞的代码，运行的任务在一个单独的线程CompletableFuture中，
 *  与主线程隔离，并且会通知主线程它的进度，成功或者失败。
 *
 *  Supplier 接口是 Java 中提供的函数式接口之一，它位于 java.util.function 包中。它表示一个结果的供应者，并且有一个称为 get() 的抽象方法，该方法不接受任何参数并返回一个结果。
 *
 * {
 *   "orderItemDtoList": [
 *            {
 * 		  "name": "IMac",
 * 		  "price": 2000,
 * 		  "quantity": 1
 *      }
 *   ]
 * }
 *
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 下单 endPoint, 创建订单
     * RequestBody 获取Json格式的请求体
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    // @TimeLimiter(name = "inventory")
    // @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){

    // public String placeOrder(@RequestBody OrderRequest orderRequest){

        // 测试 CircuitBreaker 断路器的使用

        //public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
        //supplyAsync()的方法新建另一个线程去执行！！！

        return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));
        // return  orderService.placeOrder(orderRequest);

    }

    public CompletableFuture<String>  fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Oops, Somethings went wrong!");

    }
}
