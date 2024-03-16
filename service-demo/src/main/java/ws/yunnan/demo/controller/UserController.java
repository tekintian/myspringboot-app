package ws.yunnan.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import ws.yunnan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        System.out.println("Hi User Service!");
        return userService.sayHi();
    }

    /**
     * 注意: 这里因为我们是手动简单启动的tomcat内置服务器,
     * 所以不能通过继承 WebMvcConfigurationSupport 然后重写的方式来解决中文乱码
     * 只能采用指定 produces的方式来解决返回中文乱码问题
     * @param stock
     * @return
     */
    @GetMapping(value = "/addStock/{stock}", produces = "text/html;charset=utf-8")
    public String addStock(@PathVariable("stock") int stock){
        return userService.addStock(stock);
    }
    @GetMapping(value ="/deductStock", produces = "text/html;charset=utf-8")
    public String deductStock(){
        return userService.deductStock();
    }
}
