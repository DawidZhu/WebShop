package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * 声明式编程（ Declarative）
 * 1. 声明式编程是一种编程范式，它描述了程序的逻辑，而不是控制流程。
 *
 *  监控 inventory service 状态
 * http://localhost:8082/actuator/health
 *
 *  CompletableFuture用于异步编程 asynchronous，
 *  异步编程是编写非阻塞的代码，运行的任务在一个单独的线程CompletableFuture中，
 *  与主线程隔离，并且会通知主线程它的进度，成功或者失败。
 *
 *  Supplier 接口是 Java 中提供的函数式接口之一，它位于 java.util.function 包中。它表示一个结果的供应者，并且有一个称为 get() 的抽象方法，该方法不接受任何参数并返回一个结果。
 *
 *
 * CircuitBreaker：断路器
 *   Spring Cloud Circuit Breaker supports multiple implementations of the Circuit Breaker pattern, including Netflix Hystrix, Resilience4j, and others. It provides a consistent programming model across these implementations, making it easier for developers to switch between them based on their requirements and preferences.
 * 1. 断路器是一种开关装置，当某个服务不可用时，断路器会打开，避免请求继续发送到不可用的服务上。
 * 2. 断路器的目的是在服务不可用时，避免请求继续发送到不可用的服务上，从而避免请求的堆积。
 * 3. 断路器的状态有三种：关闭、打开、半开。
 * 4. 断路器的状态由断路器的状态机控制，断路器的状态机有三种状态：关闭、打开、半开。
 * 5. 断路器的状态机的状态转换规则由断路器的配置控制。
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
    //熔断
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    //限流
    @TimeLimiter(name = "inventory")
    // 重试
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){

    // public String placeOrder(@RequestBody OrderRequest orderRequest){

        // 测试 CircuitBreaker 断路器的使用

        //supplyAsync()的方法新建另一个线程去执行！！！

        return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));
        // return  orderService.placeOrder(orderRequest);

    }

    public CompletableFuture<String>  fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Oops, Somethings went wrong!");

    }
}
