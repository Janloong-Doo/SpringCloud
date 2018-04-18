# Spring Cloud 

Author : **[Janloong Do_O](https://blog.csdn.net/du807110586)** | **<a href ="mailto: janloongdoo@gmail.com">Email</a>**

> 个人微服务框架学习,持续学习中……

> 项目中除了基础的spring cloud 组件之外,集成了第三方 **[spring boot admin](https://github.com/codecentric/spring-boot-admin)** 可视化管理组件

    
    相关环境： 
    idea 2018.1
    springboot 2.0.1
    springcloud Finchley.BUILD-SNAPSHOT
    springbootadmin 2.0.0.spnashot
    
## update 
    
    2018.04.18 rabbitmq代理的消息总线接入
    2018.04.18 高可用config-server,服务隔离测试
   

## 模块描述

|模块|描述|端口|多配置|
|:---|:---|:---|:---|
|admin-client|admin客户端 - 官方配置方式 |8890|-|
|admin-server|admin服务端|8889|-|
|config-server |高可用配置中心服务端|8888,8892|server1,server2|
|config-client |高可用配置中心客户端|8773,8893|client1,client2|
|eureka-server |高可用注册中心|8761,8762|server1,server2|
|eureka-provide |服务提供者|8763,8764|provide1,provide2|
|eureka-client2 |Ribbon+rest方式的客户端|8765|-|
|eureka-client3 |Feign方式的客户端|8766,8821(独立management端口)|-|
|eureka-client4 |普通客户端为了配合admin低版本的测试，独立pom依赖|8891|-|
|eureka-zuul |网关路由|8767|-|

## 其它描述

|模块|描述|    
|:---|:---|
|admin-server|admin官方最高RELEASE版本为1.5.7，对应的springboot相关依赖为1.5.9版本，官方新的2.0.0（对应springboot 2.0.1 ，spring官方应该在2.x阶段更推荐2.0.1）版本暂未发布release版本，需要访问国外镜像去拿到snapshot版本 |
|admin-client|这个是admin官方给出的一个客户端配置方式之一，里面会有比较完备的actuator实现效果，在通过discovery方式配置客户端方式的时候可以作参考|

## 问题记录
> 望相互学习，多多指教

1. config-server 中git的Uri连接本地 git@xxx:/repo 读取不到配置文件，未知协议原因或本地原因
2. 经部分测试，混合版本的时候依旧可以使用，但在actuator检测的时候因为1.x,2.x的默认访问路径不同在这里需要配置management的basepath,部分endpoints正常，其余的可能需要单独配置路径
3. eureka1.x服务集群与eureka2.x的放在部署至同一台服务器时出现服务的交叉注册问题。 
4. 使用rabbitmq作为代理的SpringCloud消息总线的时候。(该项仅仅为小规模测试记录,概率性)

   一是关于@RefreshScope的作用域问题，不能在启动类MainApplication使用，首先要满足的是在使用动态配置的地方使用该注解，该注解在bean相关的作用域依然可能会有其它问题。
   
   二是关于remote远程更新的时候，在向confingClient集群发送/bus-refresh时会出现两个主要问题：
   
    （1）在config-server集群的前提下，rabbitmq在通知整个服务更新时，config-server只从其中任意一个server开始fetch,
    另一个server静默。client是同时refresh,在访问client的时候会出现，相应属性值版本交叉的情况，测试中最多出现过3个版本的跨度问题。 
    
    （2）在/bus-refresh 未结束时,访问其中一个client, 会造成一个client成功 add property from server , 但是另一个client 会出现 cannot connect to rabbitmq 导致属性配置更新失败（可能并非必然联系，偶然出现）  

## 待办
    
    了解springcloudstream与springcloudbus的结合应用

## Tips:
> 为方便测试，快速建立如下效果
    
    Jetbrains intelliJ IDEA
    一、可复制other文件夹下的workspace.xml 至 .idea下覆盖相应文件
    二、复制workspace.xml中configuration节点所有内容 至 相应文件的相应节点下
    
![](./other/1.png)

