package org.goldstine.config;

import org.goldstine.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

@ComponentScan("org.goldstine.bean")
public class MainConfigOfLifeCycle {
    //生命周期
    /**
     * bean的生命周期：bean创建--->初始化-->销毁的过程
     * 容器管理bean的生命周期
     * 我们可以自定义初始化和销毁方法，容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
     * （1）指定初始化和销毁方法   相当于spring的beans.xml bean定义的init-method="" 和destory-method=""方法必须没有参数，可以抛异常
     *
     *(2)通过让bean实现InitializingBean（定义初始化逻辑）afterPropertiesSet()
     *通过DisposableBean（定义销毁逻辑）destory()
     *
     *(3)通过JSR250
     * @PostConstruct,在bean创建完成并且属性赋值完成，来执行初始化方法
     * @PreDestory:在容器销毁bean之前通知我们进行清理工作
     *
     *(4)BeanPostProcessor:bean的后置处理器
     *          在bean初始化前后进行一些处理工作
     *          postProcessBeforeInitialization():在初始化之前工作
     *          postProcessAfterInitialization():在初始化之后工作
     *
     * 遍历得到容器中所有的BeanPostProcessor，挨个执行beforeInitialization
     * 一旦返回null，跳出for循环，不会执行后变得BeanPostProcessor.postProcessorsBean
     * polulateBean给bean进行属性赋值
     *
     * spring底层对BeanPostProcessor的使用:
     * bean赋值，注入其他组件，@Autowired  ,生命周期注解功能，@Async  ,xxx BeanPostProcessor
     *
     */
//    @Scope("prototype")
    @Bean(value = "car",initMethod = "init",destroyMethod = "destory")
    public Car car(){
        return new Car();
    }
}
