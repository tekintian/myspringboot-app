# 自己动手实现的 SpringbootApplication 启动器项目

主要用于理解学习springboot的启动, 自动配置原理, 虽然很简单,但是对于新手来说还是很有参考学习价值的!



myspringboot 这个模块为自定义的springboot启动类
service-demo 这个模块为测试自定义的springbootApplication的项目


Springboot入口类 ws.yunnan.demo.MyApplication

- spingboot自定义自动配置类示例 
src/main/java/cn/tekin/config/MyWebServerAutoConfiguration.java

- 自定义启动类示例  
src/main/java/cn/tekin/MySpringbootApplication.java

- 自定义注解示例
src/main/java/cn/tekin/anno/MySpringbootApp.java

- 自定义Conditional示例
  src/main/java/cn/tekin/condition/MyTomcatCondition.java

- spring服务解耦示例
  利用springboot的自动配置功能+ @Conditional注解来解耦服务, 参考 cn/tekin/MySpringbootApplication.java 里面的webserver实现
  
## demo
默认端口8089 可以在 cn.tekin.webserver.MyTomcatWebServer里面修改端口

Redisson秒杀功能测试
- 增加库存
http://localhost:8089/addStock/999999

- 减库存
  http://localhost:8089/deductStock

- 高并发模拟减库存
~~~sh
ab -n 10000 -c 10 http://localhost:8089/deductStock
~~~




## tips

### springboot的自动配置原理
1.通过@SpringBootConfiguration 引入了@EnableAutoConfiguration(负责启动自动配置功能）
2.@EnableAutoConfiguration 引入了@import
3.Spring容器启动时：加我loc容器时会解析@import 注解
4.@import导入了一个deferredimprotSelector(它会使SpringBoot的自动配置类的顺序在最后，这样方便我们扩展和覆盖）
5.然后读取所有的/META-INF/spring.factories文件(SPI)
6.过滤出所有AutoConfigurtionClas类型的类
7.最后通过@ConditionOnXXx排除无效的自动配置类


### 为什么SpringBoot的jar可以直接运行？
1. SpringBoot提供了一个插件spring-boot-maven-plugin用于把程序打包成一个可执行的jar包。
2. Spring Boot应用打包之后，生成一个Fat jar, jar包中包含jar)，包含了应用依赖的jar包和Spring Boot loader相关的类。
3. java -jar会去找jar中的manifest文件，在那里面找到真正的启动类(main-class)：
4. Fat jar的启动Main函数是JarLauncher，它负责创建一个LaunchedURLClassLoader来加载bootlib下面的jar，井以一个新线程启动应用
   的Main函数(找到清单文件中的start-class)。

### spring如何判断pom.xml中是否加载了某个jar依赖?
通过上下文中的.getClassLoader().loadClass加载某个jar包中的标志类,然后查看是否加载成功来判断pom中是否加载了某个jar包,
如: 判断pom中是否加载了Tomcat依赖
conditionContext.getClassLoader().loadClass("org.apache.catalina.startup.Tomcat");


### tomcat启动后快速自动退出问题解决 
错误日志
~~~sh
Connected to the target VM, address: '127.0.0.1:51129', transport: 'socket'
三月 15, 2024 11:55:00 下午 org.apache.coyote.AbstractProtocol init
信息: Initializing ProtocolHandler ["http-nio-8089"]
三月 15, 2024 11:55:00 下午 org.apache.catalina.core.StandardService startInternal
信息: Starting service [Tomcat]
三月 15, 2024 11:55:00 下午 org.apache.catalina.core.StandardEngine startInternal
信息: Starting Servlet engine: [Apache Tomcat/8.5.93]
三月 15, 2024 11:55:00 下午 org.apache.coyote.AbstractProtocol start
信息: Starting ProtocolHandler ["http-nio-8089"]
Tomcat Started
Disconnected from the target VM, address: '127.0.0.1:51129', transport: 'socket'

Process finished with exit code 0
~~~
原因: 之前有启动过tmcat且非正常关闭.
解决方法: 这个需要重新启动电脑来解决这个问题!


