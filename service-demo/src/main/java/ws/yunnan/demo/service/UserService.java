package ws.yunnan.demo.service;

import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service // 这个注解的作用是将当前类当做一个组件加入到spring容器中
public class UserService {
    @Autowired
    private RedissonClient redisson;
    public String sayHi() {
        return "Hi user service!";
    }

    public String addStock(int stock){
        if (stock<1) stock=1000;
        redisson.getBucket("product:123").set(stock);
        return redisson.getBucket("product:123").get().toString();
    }
    public String deductStock(){
        String  redisKey = "product:123";
        // 注意 新版本getReadWriteLock 这里的锁名称不能是已有的rediskey
        final RReadWriteLock lock = redisson.getReadWriteLock("plock");

        lock.writeLock().lock(); // 加锁

        String msg="";

        try {
            // 从redis中获取当前剩余的库存数量
            int stock = Integer.parseInt(redisson.getBucket(redisKey).get().toString());
            if (stock > 0) {
                int realStock = stock - 1;
                // 更新库存数量
                redisson.getBucket(redisKey).set(realStock);

                msg=String.format("当前剩余库存: %d ",realStock);
            } else {
                msg="当前库存为空";
            }
        }finally {
            lock.writeLock().unlock(); // 释放锁
        }

        System.out.println(msg);

        return msg;
    }
}
