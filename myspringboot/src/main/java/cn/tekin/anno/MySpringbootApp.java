package cn.tekin.anno;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的Spingboot注解
 *
 * 注意:这里@ComponentScan的没有指定扫描的基础包, spring默认会扫描run参数中传递的类所在的包下面的所有类
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@ComponentScan(basePackages = {"cn.tekin.config","ws.yunnan.demo"})
public @interface MySpringbootApp {

}
