package ws.yunnan.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service // 这个注解的作用是将当前类当做一个组件加入到spring容器中
public class UserService {
    public String sayHi() {
        return "Hi user service!";
    }
}
