package org.goldstine.test;

import org.goldstine.tx.TxConfig;
import org.goldstine.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Tx {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);

        UserService bean = applicationContext.getBean(UserService.class);
        bean.insertUser();

        applicationContext.close();
    }

}
