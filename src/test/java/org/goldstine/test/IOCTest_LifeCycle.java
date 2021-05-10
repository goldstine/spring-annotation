package org.goldstine.test;

import org.goldstine.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_LifeCycle {
    /**
     * 创建：
     *      单实例：在容器启动的时候创建对象
     *      多实例：在每次获取的时候创建对象   applicationContext.getBean("car")
     * 初始化：
     *      对象创建完成，并赋值好，调用初始化方法
     *      多实例在调用获取bean的时候初始化
     * 销毁：
     *      单实例：容器关闭的时候
     *      多实例：容器不会管理这个bean，容器不会自动调用销毁方法，需要手动调用销毁方法
     */

    @Test
    public void test01(){
        //1、创建ioc容器，在创建ioc容器的时候，同时所有单实例的bean也会同时被创建
        //多实例在获取对应的bean的时候创建
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成.....");//此时会调用car的构造函数创建单实例，输出car constructor .....
        //要调用bean的初始化和销毁方法，直接在对应的配置类中指定
        //对象创建完成调用init初始化bean  所以在构造方法之后调用

//        Object car = applicationContext.getBean("car");
//        System.out.println(car);


        //容器在关闭的时候调用销毁方法
        applicationContext.close();  //  AnnotationConfigApplicationContext有关闭容器的方法close();ApplicationContext没有关闭容器的close()方法
        //容器销毁了，同样会调用bean的销毁方法

        //直接在配置文件加上@Scope("prototype")多实例，再多实例情况下，不会调用init() 和destory()

    }
}
