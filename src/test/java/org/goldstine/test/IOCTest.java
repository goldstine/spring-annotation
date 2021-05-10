package org.goldstine.test;

import org.goldstine.bean.Blue;
import org.goldstine.bean.Person;
import org.goldstine.config.MainConfig;
import org.goldstine.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Map;

public class IOCTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
    @Test
    public void test03(){
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);

        /**
         * IDEA切换项目运行环境   直接配置运行vm option:-ea -Dos.name=linux
         */
        //动态获取环境变量的值,windows10
        ConfigurableEnvironment environment = (ConfigurableEnvironment)applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);

        for (String s : namesForType) {
            System.out.println(s);
        }
        Map<String, Person> person = applicationContext.getBeansOfType(Person.class);
        System.out.println(person);


    }

    @SuppressWarnings("resource")
    @Test
    public void test01(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println(definitionName);
        }
    }

    @Test
    public void test02(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println(definitionName);
        }
        System.out.println("ioc容器创建完成....");
//单实例的，无论进行多少次获取，都是前面对应配置类的new的对象   指向堆里面的同一个对象
        Object bean = applicationContext.getBean("person");
        Object bean1 = applicationContext.getBean("person");
        Object bean2 = applicationContext.getBean("person");
        System.out.println(bean==bean1);
    }


    //测试创建一个Color组件，开始并没有导入容器
    @Test
    public void testImport(){
        printBeans(applicationContext);
        Blue bean = applicationContext.getBean(Blue.class);
        System.out.println(bean);

        System.out.println("========================");
        //工厂Bean获取的是调用getObject()方法创建的对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean的类型:"+colorFactoryBean.getClass());
        Object colorFactoryBean1=applicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean==colorFactoryBean1); //在ColorFactoryBean中设置为单实例

        Object colorFactoryBean2 = applicationContext.getBean("&colorFactoryBean"); //只需要加上一个&就可以获取ColorFactoryBean
        //前缀&定义在BeanFactory类中
        System.out.println(colorFactoryBean2.getClass()); //org.goldstine.bean.ColorFactoryBean@7a69b07



    }

    //打印所有的组件
    private void printBeans(ApplicationContext applicationContext){
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println(definitionName);
        }
    }

}
