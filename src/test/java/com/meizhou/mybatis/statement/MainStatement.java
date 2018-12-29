package com.meizhou.mybatis.statement;

import com.meizhou.fly.mapper.IUserMapper;
import com.meizhou.fly.model.User;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by meizhou on 2018/9/15.
 */
public class MainStatement {

    private static void newMappedStatement(Configuration configuration, String msId, SqlSource sqlSource, SqlCommandType sqlCommandType, final Class<?> resultType) {
        MappedStatement.Builder builder = new MappedStatement.Builder(configuration, msId, sqlSource, sqlCommandType);
        builder.resultMaps(Collections.singletonList(new ResultMap.Builder(configuration, "defaultResultMap", resultType, new ArrayList<ResultMapping>(0)).build()));
        if (sqlCommandType == SqlCommandType.INSERT) {
            builder.keyGenerator(new Jdbc3KeyGenerator()).keyProperty("id");
        }
        configuration.addMappedStatement(builder.build());
    }

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-statement.xml"});
        context.start();
        SqlSessionTemplate sqlSessionTemplate = context.getBean(SqlSessionTemplate.class);
        IUserMapper userMapper = sqlSessionTemplate.getMapper(IUserMapper.class);

        XMLLanguageDriver xmlLanguageDriver = new XMLLanguageDriver();
        SqlSource sqlSourceSelect = xmlLanguageDriver.createSqlSource(sqlSessionTemplate.getConfiguration(), "select * from user where id=#{id}", Integer.class);
        newMappedStatement(sqlSessionTemplate.getConfiguration(), "com.meizhou.fly.mapper.IUserMapper.selectByPrimaryKey", sqlSourceSelect, SqlCommandType.SELECT, User.class);
        SqlSource sqlSourceInsert = xmlLanguageDriver.createSqlSource(sqlSessionTemplate.getConfiguration(), "insert into user (id,name) values(#{id},#{name})", User.class);
        newMappedStatement(sqlSessionTemplate.getConfiguration(), "com.meizhou.fly.mapper.IUserMapper.insertSelective", sqlSourceInsert, SqlCommandType.INSERT, Integer.class);
        User user = new User();
        user.setName(System.currentTimeMillis() + "");
        System.out.println("~~~~~~~~~insert~~~~~~~~~");
        System.out.println(userMapper.insertSelective(user));
        System.out.println(user.getId());
        System.out.println("~~~~~~~~~select~~~~~~~~~");
        System.out.println(userMapper.selectByPrimaryKey(37));
    }
}
