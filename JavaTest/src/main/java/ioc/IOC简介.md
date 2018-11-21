控制反转IOC全称是 Inversion Of Control-IOC 和 依赖注入 Dependency Inject;在 Spring 
中是等同的概念，控制反转是通过依赖注入来实现的。所谓的以来注入指的是容器来负责创建对象以及维护对象间的以来关系，而不是通过对象本身负责自己的创建和解决自己的依赖。

以来注入的主要目的是为了解耦，体现一种""组合的概念"。

Spring IOC负责创建Bean，并将容器功能类 Bean 注入到你需要的 Bean 中。
Spring 提供了注解、xml配置、Java代码配置三种方法配置Bean