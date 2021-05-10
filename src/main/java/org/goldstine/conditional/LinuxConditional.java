package org.goldstine.conditional;

import org.goldstine.bean.Person;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxConditional implements Condition {
    /**
     *
     * @param conditionContext    判断条件能使用的上下文环境
     * @param annotatedTypeMetadata  注释信息
     * @return
     */
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //判断是否linux系统
        //能获取到ioc使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        Environment environment = conditionContext.getEnvironment();//获取当前环境信息
        //获取到bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        String property = environment.getProperty("os.name");

        //条件也可以是容器中是否包含指定的bean，如果包含
        boolean definition = registry.containsBeanDefinition("person");
    //可以判断容器中的bean注册情况，也可以给容器中注册bean
        if (property.contains("linux")) {

            return true;
        }

        return false;
    }
}
