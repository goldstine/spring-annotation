package org.goldstine.config;

import org.goldstine.bean.Color;
import org.goldstine.bean.ColorFactoryBean;
import org.goldstine.bean.Person;
import org.goldstine.bean.Red;
import org.goldstine.conditional.LinuxConditional;
import org.goldstine.conditional.MyImportBeanDefinitionRegistrar;
import org.goldstine.conditional.MyImportSelector;
import org.goldstine.conditional.WindowsConditional;
import org.springframework.context.annotation.*;
//满足当前条件，这个类中配置的所有bean注册才能生效
@Conditional({WindowsConditional.class})    //类中组件统一设置
@Configuration
//@Import(Color.class)//直接导入Color组件，id默认是组件的全类名
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})   //通过数组导入多个
public class MainConfig2 {

    @Bean("person")
    //prototype:多实例的 (默认值)   singleton单实例的   request(同一个请求创建一个实例)    session（同一个session创建一个实例）
    /**
     * prototype:多实例的ioc容器启动并不会去调用方法创建对象放到容器中，每次获取的时候才会调用方法创建对象
     * singleton:单实例，ioc容器启动就会调用方法创建对象放到ioc容器中
     * 以后每次获取就是直接从容器（map.get()）中拿
     */
//    @Scope("prototype")
    @Scope
    @Lazy
    public Person person(){
        System.out.println("给容器中添加Person....");
        return new Person("张三",25);
    }

    /**
     * 懒加载：     懒加载专门针对单实例
     * 单实例bean：默认在容器 启动的时候创建对象
     * 懒加载：容器启动的时候不创建对象，第一次使用（获取）Bean创建对象，并进行初始化
     */

    /**
     * @Conditional:按照一定的条件进行判断，满足条件给容器中支持bean
     *
     * 如果系统是windows，给容器中注册（"bill"）
     * 如果是linux系统，给容器中注册（"linus"）
     * @Conditional可以放在方法上，也可放在类上
     * @Conditional({Conditional})
     * 条件就是需要实现的接口Condition接口   该接口是：org.springframework.context.annotation.Condition
     */
    @Bean("bill")
    @Conditional({WindowsConditional.class})
    public Person person01(){
        return new Person("Bill Gates",62);
    }
    @Bean("linus")
    @Conditional({LinuxConditional.class})
    public Person person02(){
        return new Person("linus",48);
    }

    /**
     * 给容器中注册组件的方式：
     * (1)包扫描+组件标注注解（@Controller/@Service/@Repository/@Component   局限于自己写的类）
     * （2）@Bean[导入的第三方包里面的组件]
     * （3）@Import[快速给容器中导入一个组件]
     *          （1）@Import(要导入到容器中的组件)：容器中就会自动注册这个组件，id默认是全类名
     *          （2）@ImportSelector:ImportSelector接口：返回需要导入的数组的全类名数组
     *          (3) ImportBeanDefinitionRegistrar:创建接口ImportBeanDefinitionRegistrar的实现类
     *          //手动注册bean到容器中
     *  （4）使用spring提供的FactoryBean（工厂Bean）   接口工厂Bean   FactoryBean
     *          (1)默认获取到的是工厂Bean调用getObject创建的对象
     *          （2）要获取工厂Bean本身，我们需要给id前面加上一个&
     *          &colorFactoryBean
     *
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }

}
