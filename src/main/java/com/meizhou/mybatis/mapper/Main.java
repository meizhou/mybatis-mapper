package com.meizhou.mybatis.mapper;

import com.meizhou.fly.mapper.IUserMapper;
import com.meizhou.fly.model.User;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

/**
 * Created by meizhou on 2018/9/15.
 */
public class Main {

    private static void newSelectMappedStatement(Configuration configuration, String msId, SqlSource sqlSource, final Class<?> resultType) {
        MappedStatement ms = new MappedStatement.Builder(
                configuration, msId, sqlSource, SqlCommandType.SELECT)
                .resultMaps(new ArrayList<ResultMap>() {
                    {
                        add(new ResultMap.Builder(configuration,
                                "defaultResultMap",
                                resultType,
                                new ArrayList<ResultMapping>(0)).build());
                    }
                })
                .build();
        configuration.addMappedStatement(ms);
    }

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        context.start();
        context.refresh();
        SqlSessionTemplate sqlSessionTemplate = context.getBean(SqlSessionTemplate.class);
        IUserMapper userMapper = sqlSessionTemplate.getMapper(IUserMapper.class);

        XMLLanguageDriver xmlLanguageDriver = new XMLLanguageDriver();
        SqlSource sqlSource = xmlLanguageDriver.createSqlSource(sqlSessionTemplate.getConfiguration(), "select * from user", null);
        newSelectMappedStatement(sqlSessionTemplate.getConfiguration(), "com.meizhou.fly.mapper.IUserMapper.select2", sqlSource, User.class);
        System.out.println(userMapper.select2());
    }
}
