package org.goldstine.config;

import org.goldstine.bean.Car;
import org.goldstine.bean.Color;
import org.goldstine.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan({"org.goldstine.service","org.goldstine.dao","org.goldstine.controller","org.goldstine.bean"})
public class MainConfigOfAutowired {
    /**
     * 自动装配类:
     *      spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值
     *      (1)Autowired  自动注入
     *          （1）默认优先按照类型去容器中找对应的组件：applicationContext.getBean(BookDao.class);
     *          (2)
     */

    //如果容器中有多个Dao，比如通过扫描ComponentScan({})进来的Dao和  自己加入进的Dao
    //如果容器中存在多个相同类型的Dao，会自动默认按照Dao名称进行装配
    //如果需要自己指定需要装配的bean，可以使用@Autowired  配合使用 @Qualifier("bookDao") 使用@Qualifier指定需要装配的组件的id，而不是使用属性名
    //默认一定要将属性赋好值，如果容器中没有对应的bean，还是使用Autowired自动装配，则会报错

    //但是还有方式，使如果容器中有对应的bean，则可以进行自动装配，如果没有则可以不进行自动装配
    //@Autowired(required="false"）  默认情况下是必须装配的

    /**
     * @Primary 注解   让spring进行装配的时候，使用首选的bean
     *
     * 可以在没有指定的情况下，首选装配dao2   @Primary
     * 也可以明确指定dao1   @Qualifier()
     *
     */

    /**
     *(2)Spring还支持使用@Resource（jSR250）和@Inject(JSR330)规范   这两个注解都是java规范  @Autowired属于spring的注解
     * @Resource:   可以和@Autowired一样实现自动装配功能，默认是按照组件名称进行装配，当然，也可已制定装配的Dao的名称@Resource(name="bookDao2)
     *                  缺点：没有能支持@Primary功能  没有能支持@Autowired(requred=false)
     * @Inject:   需要导入一个依赖
     *<dependency>
     *             <groupId>javax.inject</groupId>
     *             <artifactId>javax.inject</artifactId>
     *             <version>1</version>
     *         </dependency>
     *默认自动装配的是BookDao2,所以Inject支持@Primary注解    和@Autowired功能一样  但是没有require=flase的功能 容器中没有的时候不进行装配
     *
     * 底层使用BeanPostProcessor
     * AutowiredAnnotationBeanPostProcessor:解析完成自动装配功能的
     *
     *
     *（3）@Autowired:在构造器，参数，方法，属性，上都可以使用该注解
     * 不管如何放置，都是从容器中获得bean对象
     *      （1）可以将@Autowired标注在方法上    @Bean+方法参数，参数从容器中获取
     *      （2）可以将@Autowired标注在构造方法上，对应的对象也是从容器中获取的
     *      如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，参数位置的组件还是可以从容器中获得
     *
     *      （3）也可以将@Autowired直接放在参数上
     *
     *
     * （4）自定义组件想要使用spring容器底层的一些组件（ApplicationContext,BeanFactory,xxx）
     *              自定义组件实现xxxxAware接口
     *自定义组件xxxAware：在创建对象的时候，会调用接口规定的方法注入相关组件
     * 把spring自己底层的一些组件注入到bean中
     *ApplicationContextAware  其实也是由BeanPostProcessor的实现类ApplicationContextAwareProcessor实现的
     * xxxAware：功能是使用xxxProcessor
     * ApplicationContextAware==>ApplicationContextAwareProcessor
     *
     *
     */

    @Primary    //首选的自动装配的bean
    @Bean("bookDao2")
//    @Bean
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }
    @Bean   //@Bean标注的方法创建对象的时候，方法参数的值从容器中获取
    public Color color(Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }

}
