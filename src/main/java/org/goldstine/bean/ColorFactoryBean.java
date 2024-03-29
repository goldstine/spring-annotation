package org.goldstine.bean;

import org.springframework.beans.factory.FactoryBean;

//创建一个Spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean<Color> {

    //返回一个Color对象，这个对象会添加到容器
    public Color getObject() throws Exception {
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 是单实例吗
     * true：这个bean是单实例，在容器中保持那一份
     * false:多实例，每次获取都会在容器中创建一个新的实例
     * @return
     */
    public boolean isSingleton() {
        return true;
    }
}
