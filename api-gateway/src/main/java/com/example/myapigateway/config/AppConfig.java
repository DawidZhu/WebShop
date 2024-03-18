package com.example.myapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Kubernetes
 * 管理Docker 容器集群
 * Kubernetes, also known as K8s, is an open-source system for automating deployment, scaling,
 * and management of containerized applications.
 * A Kubernetes cluster consists of a set of worker machines, called nodes,
 * that run containerized applications. Every cluster has at least one worker node.
 *
 * Step0: Create Docke file Object
 * Step 1: Create K8s Deployment Object，a template for creating pods
 * Step 2: create K8s Service Object
 * 在部署应用程序时，先创建Deployment来定义应用程序的运行状态，然后创建Service来暴露应用程序的访问入口。
 * Service通过标签选择器将请求路由到对应的Pod上，从而实现对应用程序的访问。
 *
 *
 * kubernetes组件
 * 一个kubernetes集群主要是由控制节点(master)、**工作节点(node)**构成，每个节点上都会安装不同的组件。
 * master：集群的控制平面，负责集群的决策 ( 管理 )
 *   ApiServer : 资源操作的唯一入口，接收用户输入的命令，提供认证、授权、API注册和发现等机制
 *   Scheduler : 负责集群资源调度，按照预定的调度策略将Pod调度到相应的node节点上
 *   ControllerManager : 负责维护集群的状态，比如程序部署安排、故障检测、自动扩展、滚动更新等
 *   Etcd ：负责存储集群中各种资源对象的信息
 *
 * node：集群的数据平面，负责为容器提供运行环境 ( 干活 )，一个Node可以是VM或物理机。
 *   Kubelet : 负责维护容器的生命周期，即通过控制docker，来创建、更新、销毁容器
 *   KubeProxy : 负责提供集群内部的服务发现和负载均衡
 *   Docker : 负责节点上容器的各种操作
 *
 * one-container-per-Pod 是 Kubernetes 最常见的模型，这种情况下，只是将单个容器简单封装成 Pod。
 * 有些容器天生就是需要紧密联系，一起工作。Pod 提供了比容器更高层次的抽象，将它们封装到一个部署单元中。
 *
 * Controller：
 *  Kubernetes 通常不会直接创建 Pod，而是通过 Controller 来管理 Pod 的。Controller 中定义了 Pod 的部署特性，
 *  比如有几个副本，在什么样的 Node 上运行等。为了满足不同的业务场景，Kubernetes 提供了多种 Controller，
 *  包括 Deployment、ReplicaSet、DaemonSet、StatefuleSet、Job 等。
 *
 *  Deployment 是最常用的 Controller，比如我们可以通过创建 Deployment 来部署应用的。
 *
 *
 * 1.4 kubernetes概念
 *
 * Master：集群控制节点，每个集群需要至少一个master节点负责集群的管控
 * Node：工作负载节点，由master分配容器到这些node工作节点上，然后node节点上的docker负责容器的运行，一个Node可以是VM或物理机。
 * Pod：kubernetes的最小控制单元，容器都是运行在pod中的，一个pod中可以有1个或者多个容器
 * Controller：控制器，通过它来实现对pod的管理，比如启动pod、停止pod、伸缩pod的数量等等
 * Service：pod对外服务的统一入口，下面可以维护者同一类的多个pod，一个Service可以看作- -组提供相同服务的Pod的对外访问接口
 * Label：标签，用于对pod进行分类，同一类pod会拥有相同的标签
 * NameSpace：命名空间，用来隔离pod的运行环境
 *
 * Kubectl : 是kubernetes 的集群命令行工具，通过kubectl 能够对集群本身进行管理，并能够在集群上进行容器化应用的安装和部署
 * kubelet：master 派到node 节点代表，管理本机容器
 * 命令语法： kubectl [command] [type] [name] [flags]
 * command: 对资源进行的操作，creat，get，delete
 * type: 资源类型，deployment，pod，service
 *
 * kubectl get pods # 查看所有pod
 *  kubectl get svc  # 查看所有service
 *   kubectl get deployments  # 查看所有deployment
 * kubectl get pod podname # 参看某个pod
 * kubectl get node
 * kubectl get svc
 * kubectl logs podname
 *
 */
@Configuration
public class AppConfig {

    /**
     * 目的： RestTemplate Bean注入IOC
     */
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
