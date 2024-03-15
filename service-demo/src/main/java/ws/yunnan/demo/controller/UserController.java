package ws.yunnan.demo.controller;

import ws.yunnan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String test(){
        System.out.println("User Service!@");
        return userService.sayHi();
    }
}
