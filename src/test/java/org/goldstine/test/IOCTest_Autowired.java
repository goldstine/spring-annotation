package org.goldstine.test;

import org.goldstine.bean.Boss;
import org.goldstine.bean.Car;
import org.goldstine.bean.Color;
import org.goldstine.config.MainConfigOfAutowired;
import org.goldstine.dao.BookDao;
import org.goldstine.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Autowired {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

//        BookService bookService = applicationContext.getBean(BookService.class);
//        System.out.println(bookService);

//        BookDao bean = applicationContext.getBean(BookDao.class);
//        System.out.println(bean);

        Boss bean = applicationContext.getBean(Boss.class);
        System.out.println(bean);
        Car bean1 = applicationContext.getBean(Car.class);
        System.out.println(bean1);//这两个Car都是从容器中获得的bean，是同一个bean

        System.out.println("=========================");
        Color bean2 = applicationContext.getBean(Color.class);
        System.out.println(bean2);
        Car bean3 = applicationContext.getBean(Car.class);
        System.out.println(bean3);

        System.out.println("测试的ioc容器："+applicationContext);

        applicationContext.close();
    }

}
