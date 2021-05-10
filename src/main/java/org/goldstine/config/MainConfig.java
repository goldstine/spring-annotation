package org.goldstine.config;

import org.goldstine.bean.Person;
import org.goldstine.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//配置类相当于一个配置文件
@Configuration   //告诉spring这是一个配置类
@ComponentScan(value="org.goldstine",includeFilters = {
        @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = {Controller.class}),
        @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,classes={BookService.class}),
        @ComponentScan.Filter(type=FilterType.CUSTOM,classes = {MyTypeFilter.class})
},useDefaultFilters = false)

//FilterType.ASSIGNABLE_TYPE按照给定的类型进行过滤
//FilterType.ASPECTJ  使用ASPECTJ表达式
//FilterType.REGEX   使用正则指定过滤规则
//FilterType.CUSTOM   使用自定义的过滤规则

//可以通过@ComponentScans()或者@Component()重复指定扫描规则
//@ComponentScan value:指定要扫描的包
//excludeFilters=Filter[]  指定扫描的时候按照什么规则排除那些组件
//includeFilters=Filter[]  指定扫描的时候只需要包含那些组件
public class MainConfig {

    @Bean("Person")   //给容器中注册一个bean   类型为返回值的类型，id默认使用方法名作为id
    public Person person12(){
        return new Person("lisi",20);
    }
}
