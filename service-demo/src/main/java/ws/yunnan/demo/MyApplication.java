package ws.yunnan.demo;

import cn.tekin.MySpringbootApplication;
import cn.tekin.anno.MySpringbootApp;

@MySpringbootApp
public class MyApplication {

    public static void main(String[] args) {
        // 这里的MyApplication.class 就是传入Spring容器的配置类, 一般是使用的当前的类, 也可以是其他的类
        MySpringbootApplication.run(MyApplication.class, args);
    }

}