package com.meizhou.mybatis.mapper;

import com.meizhou.fly.mapper.IUserMapper;
import com.meizhou.fly.model.User;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by meizhou on 2018/9/15.
 */
public class Main {

    private static void newMappedStatement(Configuration configuration, String msId, SqlSource sqlSource, SqlCommandType sqlCommandType, final Class<?> resultType) {
        MappedStatement.Builder builder = new MappedStatement.Builder(configuration, msId, sqlSource, sqlCommandType);
        builder.resultMaps(Collections.singletonList(new ResultMap.Builder(configuration, "defaultResultMap", resultType, new ArrayList<ResultMapping>(0)).build()));
        if (sqlCommandType == SqlCommandType.INSERT) {
            builder.keyGenerator(new Jdbc3KeyGenerator()).keyProperty("id");
        }
        configuration.addMappedStatement(builder.build());
    }

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        context.start();
        context.refresh();
        SqlSessionTemplate sqlSessionTemplate = context.getBean(SqlSessionTemplate.class);
        IUserMapper userMapper = sqlSessionTemplate.getMapper(IUserMapper.class);


        Collection<Class<?>> mappers = sqlSessionTemplate.getConfiguration().getMapperRegistry().getMappers();
        for (Class<?> clazz : mappers) {
            for (Method method : clazz.getMethods()) {
                System.out.println(method.getName());
            }
        }
        XMLLanguageDriver xmlLanguageDriver = new XMLLanguageDriver();
        SqlSource sqlSource = xmlLanguageDriver.createSqlSource(sqlSessionTemplate.getConfiguration(), "select * from user where id=#{id}", Integer.class);
        newMappedStatement(sqlSessionTemplate.getConfiguration(), "com.meizhou.fly.mapper.IUserMapper.select2", sqlSource, SqlCommandType.SELECT, User.class);

        SqlSource sqlSource2 = xmlLanguageDriver.createSqlSource(sqlSessionTemplate.getConfiguration(), "insert into user (id,name) values(#{id},#{name})", User.class);
        newMappedStatement(sqlSessionTemplate.getConfiguration(), "com.meizhou.fly.mapper.IUserMapper.insertSelective", sqlSource2, SqlCommandType.INSERT, Integer.class);
        User user = new User();
//        user.setId(91);
        user.setName("fghgfg");
        System.out.println(userMapper.insertSelective(user));
        System.out.println(user.getId());
//        System.out.println(userMapper.select2(37));
//        System.out.println(userMapper.select());
//        System.out.println(userMapper.insert2(37));
    }
}
