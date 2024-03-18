package com.example.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Spring Boot Webflux
 *  WebFlux是完全非阻塞的。 non-blocking. Feign是 blocking
 * 就是基于 Reactor 实现的。Spring Boot 2.0 包括一个新的 spring-webflux 模块。
 * 该模块包含对响应式 HTTP 和 WebSocket 客户端的支持，以及对 REST，HTML 和 WebSocket
 * 交互等程序的支持。一般来说，Spring MVC 用于同步处理，Spring Webflux 用于异步处理。
 * reactive web components RestController and WebClient.
 * WebClient, is a non-blocking client with support for reactive streams.
 *
 * 微服务体系结构，WebFlux 和 MVC 可以混合使用。尤其开发 IO 密集型服务的时候，选择 WebFlux 去实现。
 *
 * WebClient可以发送异步Web请求，并支持响应式编程。
 *
 * Reactive programming
 *
 * 响应式编程是基于异步和事件驱动的非阻塞程序，只是垂直通过在 JVM 内启动少量线程扩展，
 * 而不是水平通过集群扩展。
 *
 *   1. @Configuration
 *
 *   表示声明一个 Java 形式的配置类，Spring Boot 提倡基于 Java 的配置，相当于之前在 xml 中配置 bean
 *   用于设定当前类为配置类，等于以前的Bean 配置文件. 单实例对象
 *
 *  2. @ ComponentScan() 设定扫描路径
 *  标注哪些路径下的类需要被Spring扫描，用于自动发现和装配一些Bean对象，默认配置是扫描当前文件夹下和子目录下的所有类
 *  自定义Bean，使用 @Configuration + @Bean. bean name默认为方法名，类似于以前的<bean></>
 *
 *  @ Component 的3个衍生注解,效果一样，标注在实现类上的
 *  @ Controller，@ Service，@ Repository
 *   自动定义Bean，使用： @Service / @Component 等,将对象注入到IOC容器中作为Bean，
 *  * 等于 <bean id="service..." class = "com.serviceImpl"/>
 *
 *  定义bean : @ Component, Service, Controller
 *  设置依赖注入： @ Autowired @ qualifier
 *  配置第三方bean: @Bean
 *  作用范围：@ Scope
 *
 *  @ Bean
 *   目的：管理第三方bean
 *   相当于 xml 中配置 Bean，意思是产生一个 bean 对象，并交给spring管理，
 *
 *   @ ConditionalOnBean(name = "admin")
 *   条件装配，在满足某条件的情况下执行
 *   可以通过@Conditional注解实现按条件装载bean对象
 *
 */
@Configuration //设定为配置类，取代 bean xml文件
public class WebClientConfig {

    /**
     * Builder WebClient
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
}
