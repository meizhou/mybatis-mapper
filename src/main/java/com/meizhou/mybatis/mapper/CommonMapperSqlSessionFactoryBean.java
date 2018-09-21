package com.meizhou.mybatis.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.util.Collection;

/**
 * Created by meizhou on 2018/9/21.
 */
public class CommonMapperSqlSessionFactoryBean extends SqlSessionFactoryBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        SqlSessionFactory sqlSessionFactory = this.getObject();
        Collection<Class<?>> mappers = sqlSessionFactory.getConfiguration().getMapperRegistry().getMappers();
        for (Class<?> clazz : mappers) {
            System.out.println(clazz.getName());
        }
    }
}
