[toc]
# Code Study

> 代码、框架等学习记录,整体为n=1+1+1+...的过程,非1+1+1+...=n的过程。

## 模块列表

- [bast-tool](./base-tool): 基础框架包
  - [base-framework](/base-tool/base-framework): 
  - [base-parent](.//base-tool/base-parent): 基础自定义父依赖模块
  - [common](./base-tool/common): 日常开发基础模块,已提出作为 [spring-boot-starter-common](https://github.com/Janloong-Doo/spring-boot-starter-common) 单独维护
- [cloud-alibaba](./cloud-alibaba): Spring Cloud Alibaba
  - [cloud-consumer](./cloud-alibaba/cloud-consumer): 消费客户端
  - [cloud-provider](./cloud-alibaba/cloud-provider): 服务提供者
  - [cloud-gateway](./cloud-alibaba/cloud-gateway): 网关中心
- [spring-cloud](./spring-cloud): springcloud模块
  - [admin-client](./spring-cloud/admin-client): admin监控客户端
  - [admin-server](./spring-cloud/admin-server): admin server端
  - [auth2-center](./spring-cloud/auth2-center): auth2鉴权中心
  - [config-client](./spring-cloud/config-client): 配置中心客户端
  - [config-repo](./spring-cloud/config-repo): 本地化配置源
  - [config-server](./spring-cloud/config-server): 配置中心 server端
  - [eureka-feign](./spring-cloud/eureka-feign): feign客户端
  - [eureka-provide](./spring-cloud/eureka-provide): 服务提供端
  - [eureka-ribbon](./spring-cloud/eureka-ribbon): ribbon客户端
  - [eureka-server](./spring-cloud/eureka-server): eureka注册中心
  - [gateway](./spring-cloud/gateway): gateway网关中心
  - [user](./spring-cloud/user): 用户模块
  - [zipkin-server](./spring-cloud/zipkin-server): zipkin服务端
  - [zuul](./spring-cloud/zuul): zuul网关中心
- [spring-demo](./spring-demo): springboot的单例demo合集
  - [demo-test](./spring-demo/demo-test): demo测试模块
  - [spring-boot-starter-custom](./spring-demo/spring-boot-starter-custom):
    自定义的starter
  - [spring-data-jpa](./spring-demo/spring-data-jpa): jpa的demo用法
  - [spring-mongodb-demo](./spring-demo/spring-mongodb-demo):
    mongodb的使用demo
  - [spring-parent-demo](./spring-demo/spring-parent-demo): 自定义parent
    POM依赖
  - [spring-redis-demo](./spring-demo/spring-redis-demo): redis的使用
  - [spring-security](#Spring-Security): spring security集成oauth2+jwt 以及各个组件中间件demo
  - [webcrawler](./spring-demo/webcrawler): webmagic爬虫demo
- [spring-dubbo](./spring-dubbo): dubbo的demo
  - [dubbo-client](./spring-dubbo/dubbo-client): dubbo客户端
  - [dubbo-server](./spring-dubbo/dubbo-server): dubbo服务端
- [jdk-study](./jdk-study): jdk学习
- [build-config](./build-config): 多环境变量管理
  - [dev](./build-config/dev): dev环境 
  - [prod](./build-config/prod): prod环境
- [request](./request): HttpRequest请求合集 
  - [cloud](./request/cloud): springcloud请求
  - [spring-demo](./request/spring-demo): springdemo请求

## 模块详情
> 模块中整合的详细内容

### [Spring-Security](./spring-demo/spring-security)
> 考虑到多应用模块的不便性，后续会将较多应用场景demo耦合在一个项目中

1. security + oauth2 + jwt

* 单服务下整合了授权服务中心和资源认证中心
* 基于security + oauth2 的 账户密码登录认证模式 （可自定义隐式忽略clientId，Sercert,Grant_Type等参数）
* 基于oauth2的token授权认证模式
* 基于jwt的非对称加密token（微服务场景下的对称加密token）
* 自定义登录成功，登录失败，注销成功处理器，

2. Spring Data Jpa
 
3. RabbitMq

4. Quartz

5. Redis

6. Zookeeper

7. Schedule
 

##