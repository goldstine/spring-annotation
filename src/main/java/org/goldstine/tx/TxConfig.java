package org.goldstine.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@EnableTransactionManagement    //开启事务管理功能
@ComponentScan("org.goldstine.tx")
@Configuration
public class TxConfig {

    /**
     * 声明式事务：
     * 环境搭建：
     * 1、导入相关依赖
     *      数据源，数据库驱动，Spring-jdbc模块
     * 2、配置数据源，jdbcTemplate操作数据（Spring提供的简化数据库操作的工具）操作数据
     *
     *3、给方法上标注@Transactional，表示这个方法是一个事务
     *4、在配置类上开启事务管理功能 @EnableTransactionManagement
     *
     * 5、可能报异常NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.transaction.TransactionManager'
     * 所以需要配置事务管理器来控制     TransactionManager
     *
     * 事务的原理：
     * @EnableTransactionManagement
     *
     *
     */

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
//spring对@Configuration类会特殊处理，给容器中加组件的方法，多次调用都只是从容器中找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;

    }

    //配置事务管理器
    @Bean  //注册事务管理器在容器
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

}
