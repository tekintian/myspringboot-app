package cn.tekin.webserver;

import org.springframework.web.context.WebApplicationContext;

/**
 * WebServer的抽象接口, 使用这个接口后就可以在Spring容器中通过
 * applicationContext.getBeansOfType(MyWebServer.class) 来获取所有的服务实现对象
 * 然后就可以使用 @Conditional(xxx.class) , xxx为实现了Condition接口的判断类
 * Spring中判断pom.xml是否加载了某个jar包依赖的方法, 使用 conditionContext.getClassLoader().loadClass("xxx.XXMainClassName");
 * 可以通过在上下文中加载某个jar包中的主要类来判断, 如果加载成功表示依赖了某个jar包,否则pom中没有依赖指定的包,
 * 注意这里的 xxx.XXMainClassName 为你要判断的jar包中的主要标志性类的完整Reference地址.
 * 如: conditionContext.getClassLoader().loadClass("org.apache.catalina.startup.Tomcat");
 */
public interface MyWebServer {
    public void start(WebApplicationContext applicationContext);
}
