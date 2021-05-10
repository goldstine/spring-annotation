package org.goldstine.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean, DisposableBean {
    /**
     * 通过第二种方式定义bean的初始化和销毁逻辑
     */
    public Cat(){
        System.out.println("cat constructor.....");
    }

    public void destroy() throws Exception {
        System.out.println("cat destory....");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("cat afterPropertiesSet.....");
    }
}
