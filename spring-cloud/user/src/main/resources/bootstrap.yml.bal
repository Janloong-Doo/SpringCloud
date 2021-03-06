spring:
  cloud:
    config:
      label: master
      profile: test
      uri: http://localhost:8888/   # 加入eureka服务后可以直接配置服务名
      #      discovery:
      #        enabled: true
      #        serviceId: config-server
      name: ${spring.application.name},common
    bus:
      enabled: true
      refresh:
        enabled: true
      trace:
        enabled: true
  rabbitmq:
    port: ${janloong.rabbitmq.port}
    host: ${janloong.rabbitmq.ip}
    username: ${janloong.rabbitmq.username}
    password: ${janloong.rabbitmq.password}
#  zipkin:
#    sender:
#      type: rabbit
#    #    base-url: http://${janloong.zipkin.ip}:${janloongdoo.zipkin.port}
#    #    baseUrl: http://192.168.211.135:9411
#    baseUrl: http://localhost:9411
#    message-timeout: 5
#    service:
#      name: ${spring.application.name}
  #    discovery-client-enabled: true
  #    locator:
  #      discovery:
  #        enabled: true
  #    base-url: http://192.168.211.135:9411
  sleuth:
    sampler:
      probability: 0.5f
eureka:
  instance:
    appname: ${spring.application.name}
  #    preferIpAddress: true
  #    instance-id: ${janloong.eureka.ip}:${server.port}
  client:
    service-url:
      defaultZone: http://${janloong.eureka.ip}:${janloong.eureka.port}/eureka,${janloong.eureka.other-urls}
