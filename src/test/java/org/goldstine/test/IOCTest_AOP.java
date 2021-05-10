package org.goldstine.test;

import org.goldstine.aop.MathCalculator;
import org.goldstine.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_AOP {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        /**
         * 错误的测试方式，不能直接new对象bean进行测试
         * Calculator calculator = new Calculator();
         * calculator.div(1,1);
         * 而是因该从容器中获取对象进行测试
         */

        MathCalculator bean = applicationContext.getBean(MathCalculator.class);
        bean.div(1,1);
        System.out.println(bean);

        applicationContext.close();
    }

}
