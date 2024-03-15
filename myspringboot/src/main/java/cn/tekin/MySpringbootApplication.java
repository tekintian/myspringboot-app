package cn.tekin;

import cn.tekin.webserver.MyWebServer;
import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;

/**
 * 自定义的SpringBootApplication启动类
 */
public class MySpringbootApplication {
    public static void run(Class clazz, String[] args) {
        // 创建一个Spring容器
        AnnotationConfigWebApplicationContext applicationContext= new AnnotationConfigWebApplicationContext();
        // 注册启动类
        applicationContext.register(clazz);
        // 刷新容器
        applicationContext.refresh();


      // 从spring容器中获取WebServer bean 这样就可以解耦 避免if else判断要使用那个WebServer了
        MyWebServer webserver = getWebServer(applicationContext);
        webserver.start(applicationContext);
    }

    /**
     * 这里通过使用上下文中的 getBeansOfType 方法,通过将接口类来获取容器中的所有实现类,从而达到解耦的目的.
     * @param applicationContext
     * @return
     */
    private static MyWebServer getWebServer(WebApplicationContext applicationContext) {
        // 从spring容器中获取WebServer Bean对象
        Map<String, MyWebServer> webservers = applicationContext.getBeansOfType(MyWebServer.class);
        if (webservers.isEmpty()) {
            throw new NullPointerException();
        }

        // 返回map中的第一个对象; 如果有多个Bean, 这里只返回第一个
        return webservers.values().stream().findFirst().get();
    }


}
