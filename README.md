# ??????? SpringbootApplication ?????

????????springboot???, ??????, ?????,???????????????????!



myspringboot ?????????springboot???
service-demo ???????????springbootApplication???


Springboot??? ws.yunnan.demo.MyApplication

- spingboot?????????? 
src/main/java/cn/tekin/config/MyWebServerAutoConfiguration.java

- ????????  
src/main/java/cn/tekin/MySpringbootApplication.java

- ???????
src/main/java/cn/tekin/anno/MySpringbootApp.java

- ???Conditional??
  src/main/java/cn/tekin/condition/MyTomcatCondition.java

- spring??????
  ??springboot???????+ @Conditional???????, ?? cn/tekin/MySpringbootApplication.java ???webserver??
  

## tips

### springboot???????
1.??@SpringBootConfiguration ???@EnableAutoConfiguration(???????????
2.@EnableAutoConfiguration ???@import
3.Spring????????loc??????@import ??
4.@import?????deferredimprotSelector(???SpringBoot?????????????????????????
5.???????/META-INF/spring.factories??(SPI)
6.?????AutoConfigurtionClas????
7.????@ConditionOnXXx??????????


### ???SpringBoot?jar???????
1. SpringBoot???????spring-boot-maven-plugin??????????????jar??
2. Spring Boot???????????Fat jar, jar????jar)?????????jar??Spring Boot loader?????
3. java -jar???jar??manifest???????????????(main-class)?
4. Fat jar???Main???JarLauncher????????LaunchedURLClassLoader???bootlib???jar????????????
   ?Main??(????????start-class)?

### spring????pom.xml????????jar???
???????.getClassLoader().loadClass????jar??????,?????????????pom????????jar?,
?: ??pom??????Tomcat??
conditionContext.getClassLoader().loadClass("org.apache.catalina.startup.Tomcat");



