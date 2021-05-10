package org.goldstine.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
//将后置处理器加入容器
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * @param bean   初始化返回的bean实例
     * @param beanName   bean的名字
     * @return
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization....."+bean+"===>"+beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessotAfterInitialization...."+bean+"===>"+beanName);
        return bean;
    }
}
