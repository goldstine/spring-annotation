package org.goldstine.config;

import org.goldstine.aop.LogAspects;
import org.goldstine.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    /**
     * AOP:动态代理
     *      指在程序运行期间动态地将某段代码切入到指定方法的指定位置进行运行的方式
     *
     *   1.导入aop模块：Spring aop:(spring.aspects)
     *2、定义一个业务逻辑类（MathCalculator）  :在业务逻辑运行的时候将日志进行打印（方法之前，方法运行结束，方法出现异常）
     * 3、定义一个日志切面类（LogAspects）:切面类里面的方法需要动态感知MathCalculator.java运行到哪里了
     *      通知方法：
     *          前置通知(@Before)：logStart():在目标方法（div）运行之前运行
     *          后置通知(@After)：logEnd():在目标方法运行结束之后运行    无论方法正常结束还是异常结束
     *          返回通知(@AfterReturn)：logReturn():在目标方法正常返回之后运行
     *          异常通知(@AfterThrowing)：logException():在目标方法出现异常之后运行
     *          环绕通知(@Around)：动态代理，手动推进目标方法运行（joinPoint.procced()）
     *4/给切面类标注何时进行通知
     * 5、将切面类和业务逻辑类（目标方法所在类）都加入到容器中
     *
     * 6、还必须告诉spring IOC容器，哪一个类是切面类，哪一个是业务类，只需要在切面类上加一个注解就可以   @Aspect告诉spring这个类是一个切面类
     *7、给配置类中加@EnableAspectJAutoProxy   开启基于注解版的aop模式
     *
     *在spring中有很多@Enablexxx,是用于开启某一项功能
     *
     * JoinPoint参数一定要写在前面，否则不能使用
     *
     * 三步：
     * （1）将业务逻辑组件和切面类都加入到容器中，告诉spring哪个切面类（@Aspect）
     * (2）在切面类上的每一个通知方法上标注通知注解，高数spring何时何地运行（切入点表达式）
     * （3）开启基于注解的aop模式，@EnableAspectJAutoProxy
     *
     * (二)Aop的原理
     * @EnableAspectJAutoProxy
     *(1)首先导入@Import({AspectJAutoProxyRegistrar.class})
     *主要看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么
     *
     */
    //将业务逻辑类加入切面中
    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    //将切面类也加入到容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
