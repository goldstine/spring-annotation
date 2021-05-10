package org.goldstine.bean;

import org.springframework.stereotype.Component;

@Component
public class Car {
    public Car(){
        System.out.println("car constructor .....");

    }

    public void init(){
        System.out.println("car  init.....");   //一般初始化数据源
    }
    public void destory(){
        System.out.println("car destory.....");  //一般释放资源，比如链接的数据源
    }

}
