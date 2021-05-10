package org.goldstine.config;

import org.goldstine.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量中
//怪不得以前从配置文件中获取不到值
@PropertySource(value={"classpath:/person.properties"})//@Repeatable(PropertySources.class):PropertySource是可以重复的注解
// String[] value();@PropertySource({...})也可以通过数组的方式指定多个配置文件
@Configuration
public class MainConfigOfPropertyValues {

    /**
     * 属性赋值的方式：
     * （1）使用@Value赋值：相当于beans.xml文件中指定name age的方式
     *          1、可以直接写字面量@Value("zhangsan")
     *          2、可以写SpEl；spring的el表达式#{}   @Value(#{20-2})
     *          3、可以写${}获得配置文件中属性值   @Value(${name})
     */
    @Bean
    public Person person(){
        return new Person();
    }

}
