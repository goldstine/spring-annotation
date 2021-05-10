package org.goldstine.test;

import org.goldstine.bean.Yellow;
import org.goldstine.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class IOCTest_Profile {

    /**
     * 切换到测试环境进行运行
     * （1）使用命令行参数   eclipse vm arguments:在虚拟机参数位置：-Dspring-profiles.active=test     -Dspring-profiles.active=dev     -Dspring-profiles.active=pprod
     *（2）在创建ioc容器的时候设置测试环境启动（如下所示）   代码的方式激活
     * （）
     *
     */
    @Test
    public void test01(){
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //1、创建一个applicationContext
        //2、设置需要激活的环境
//        applicationContext.getEnvironment().setActiveProfiles("test","dev");
        applicationContext.getEnvironment().setActiveProfiles("test");
        //注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        //4、启动刷新容器
        applicationContext.refresh();

        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        Yellow bean = applicationContext.getBean(Yellow.class);
        System.out.println(bean);

        applicationContext.close();
    }

}
