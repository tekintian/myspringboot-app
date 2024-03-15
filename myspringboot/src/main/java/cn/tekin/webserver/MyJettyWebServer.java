package cn.tekin.webserver;

import org.springframework.web.context.WebApplicationContext;

public class MyJettyWebServer implements MyWebServer {
    @Override
    public void start(WebApplicationContext applicationContext) {
        System.out.println("jetty started");

    }
}
