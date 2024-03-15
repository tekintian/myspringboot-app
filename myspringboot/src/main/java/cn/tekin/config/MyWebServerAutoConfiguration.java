package cn.tekin.config;

import cn.tekin.condition.MyJettyCondition;
import cn.tekin.webserver.MyJettyWebServer;
import cn.tekin.condition.MyTomcatCondition;
import cn.tekin.webserver.MyTomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyWebServerAutoConfiguration implements MyAutoConfiguration {

    @Bean
    @Conditional(MyTomcatCondition.class)
    public MyTomcatWebServer tomcatWebServer() {
        return new MyTomcatWebServer();
    }

    @Bean
    @Conditional(MyJettyCondition.class)
    public MyJettyWebServer jettyWebServer() {
        return new MyJettyWebServer();
    }

}
