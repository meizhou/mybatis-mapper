package com.meizhou.mybatis.mapper;


import com.meizhou.fly.mapper.IUserInfoMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by meizhou on 2018/9/15.
 */
public class MainXml {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        context.start();
        SqlSessionTemplate sqlSessionTemplate = context.getBean(SqlSessionTemplate.class);
        IUserInfoMapper userMapper = sqlSessionTemplate.getMapper(IUserInfoMapper.class);
        userMapper.getByPrimaryKey(1);
        userMapper.select2();
    }
}
