package cn.tekin;

import cn.tekin.config.MyAutoConfiguration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 *
 * 从 META-INF/services/cn.tekin.config.MyWebServerAutoConfiguration 文件中获取自动配置类的名字，并导入到Spring容器中，从而Spring容器就知道了这些配置类的存在，
 */
public class MyImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        ServiceLoader<MyAutoConfiguration> serviceLoader = ServiceLoader.load(MyAutoConfiguration.class);

        List<String> list = new ArrayList<>();
        for (MyAutoConfiguration autoConfiguration : serviceLoader) {
            list.add(autoConfiguration.getClass().getName());
        }

        return list.toArray(new String[0]);
    }
}
