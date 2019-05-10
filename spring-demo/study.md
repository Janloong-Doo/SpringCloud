# spring 知识点记录

## bean的使用


| bean名称 | 描述 | 
|:---:|:---:| 
| ApplicationRunner | 随springboot启动的runner类 |
| | |


## 注解
| 注解名称| 描述 | 
|:---:|:---:| 
|ConditionalOnBean| 仅仅在当前上下文中存在某个对象时，才会实例化一个Bean|
|ConditionalOnExpression| 当表达式为true的时候，才会实例化一个Bean|
|ConditionalOnMissingBean| 仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean|
|ConditionalOnMissingClass| 某个class类路径上不存在的时候，才会实例化一个Bean|
|ConditionalOnNotWebApplication| 不是web应用|
|ConditionalOnClass| 当注解在方法上，某个class位于类路径上，才会实例化一个Bean|
|ConditionalOnClass| 当注解于类上, 某个class位于类路径上，否则不解析该注解修饰的配置类|