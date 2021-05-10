package org.goldstine.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.goldstine.bean.Yellow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

//@Profile("test")
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

/**
 * Profile：
 *  Spring为我们提供的可以根据当前的环境，动态地激活和切换一些列组建的功能
 *
 *  开发环境，测试环境，生产环境   不同的环境中的组件不同
 *  数据源（/A）(/B)(/C)
 *
 * @Profile :指定组件在哪一个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 *加了环境标识的bean，只有这个环境被激活才能注册到容器中
 * @Profile(default)  默认是default环境
 *
 * @Profile:也可以写在类上，表示只有对应的环境激活，整个类才生效
 *
 * 没有标注环境标识的bean，在任何环境下都是加载的
 *
  */

    private StringValueResolver valueResolver;

    private String driverClass;

//    @Profile("test")
    @Bean
    public Yellow yellow(){
        return new Yellow();
    }


    @Value("${db.user")
    private String user;
//    @Value("${db.password}")
//    private String password;
//    @Value("${db.driverClass}")
//    private String driverClass;

//    @Profile(default)
    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourcetest(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");

        dataSource.setDriverClass(driverClass);
        return dataSource;
    }


    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.valueResolver=stringValueResolver;
        driverClass = valueResolver.resolveStringValue("${db.driverClass}");
    }
}
