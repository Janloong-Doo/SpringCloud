# Spring Cloud 

Author : **<a href ="mailto: janloongdoo@gmail.com">Janloong Do_O</a>**

> 个人微服务框架学习,持续学习中……

> 项目中除了基础的spring cloud 组件之外,集成了第三方 **[spring boot admin](https://github.com/codecentric/spring-boot-admin)** 可视化管理组件

## 模块描述

|模块|描述|
|:---|:---| 
|admin-client|admin客户端 - 官方配置方式 |
|admin-server|admin服务端|
|config-server |配置中心服务端|
|config-client |配置中心客户端|
|eureka-server |注册中心 - 默认提供server1,server2两个环境|
|eureka-provide |服务提供者 - 默认提供provide1，provide2|
|eureka-client2 |Ribbon+rest方式的客户端|
|eureka-client3 |Feign方式的客户端|
|eureka-client4 |普通客户端为了配合admin低版本的测试，独立pom依赖|
|eureka-zuul |网关路由|

## 其它描述

|模块|描述|    
|:---|:---|
|admin-server|admin官方最高RELEASE版本为1.5.7，对应的springboot相关依赖为1.5.9版本，官方新的2.0.0（对应springboot 2.0.1 ，spring官方应该在2.x阶段更推荐2.0.1）版本暂未发布release版本，需要访问国外镜像去拿到snapshot版本 |
|admin-client|这个是admin官方给出的一个客户端配置方式之一，里面会有比较完备的actuator实现效果，在通过discovery方式配置客户端方式的时候可以作参考|

## 问题记录
> 望相互学习，多多指教

1. config-server 中git的Uri连接本地 git@xxx:/repo 读取不到配置文件，未知协议原因或本地原因
2. 经部分测试，混合版本的时候依旧可以使用，但在actuator检测的时候因为1.x,2.x的默认访问路径不同在这里需要配置management的basepath,部分endpoints正常，其余的可能需要单独配置路径
    



