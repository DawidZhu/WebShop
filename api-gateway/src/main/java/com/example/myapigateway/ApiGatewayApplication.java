package com.example.myapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * http://127.0.0.1:8181/api/product
 * 12-factors
 * I. 基准代码
 * 一份基准代码，多份部署
 * I. Codebase
 * One codebase tracked in revision control, many deploys
 * II. 依赖
 * 显式声明依赖关系
 * II. Dependencies
 * Explicitly declare and isolate dependencies
 * III. 配置
 * 在环境中存储配置
 * III. Config
 * Store config in the environment
 * IV. 后端服务
 * 把后端服务当作附加资源
 * IV. Backing services
 * Treat backing services as attached resources
 * V. 构建，发布，运行
 * 严格分离构建和运行
 * V. Build, release, run
 * Strictly separate build and run stages
 * VI. 进程
 * 以一个或多个无状态进程运行应用
 * VI. Processes
 * Execute the app as one or more stateless processes
 * VII. 端口绑定
 * 通过端口绑定提供服务
 * VII. Port binding
 * Export services via port binding
 * VIII. 并发
 * 通过进程模型进行扩展
 * VIII. Concurrency
 * Scale out via the process model
 * IX. Disposability
 * Maximize robustness with fast startup and graceful shutdown
 * X. 开发环境与线上环境等价
 * 尽可能的保持开发，预发布，线上环境相同
 * X. Dev/prod parity
 * Keep development, staging, and production as similar as possible
 * XI. 日志
 * 把日志当作事件流
 * XI. Logs
 * Treat logs as event streams
 * XII. Admin processes
 * Run admin/management tasks as one-off processes
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

    // API gateway 不需要代码，配置即可

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
