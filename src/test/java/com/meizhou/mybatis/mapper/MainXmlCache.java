package com.meizhou.mybatis.mapper;


import com.meizhou.fly.mapper.IUserInfoMapper;
import com.meizhou.fly.model.UserInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by meizhou on 2018/9/15.
 */
public class MainXmlCache {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext-cache.xml"});
        context.start();
        SqlSessionTemplate sqlSessionTemplate = context.getBean(SqlSessionTemplate.class);
        IUserInfoMapper userMapper = sqlSessionTemplate.getMapper(IUserInfoMapper.class);
        UserInfo userInfo110 = userMapper.getByPrimaryKey(110);
        if (userInfo110 != null) {
            System.out.println("name==>" + userInfo110.getName());
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(110);
        userInfo.setName("update_220");
        userMapper.updateSelective(userInfo);
        UserInfo userInfo110Update = userMapper.getByPrimaryKey(110);
        if (userInfo110Update != null) {
            System.out.println("name==>" + userInfo110Update.getName());
        }

        SimpleModelCriteria simpleModelCriteria = new SimpleModelCriteria();
        simpleModelCriteria.eq(UserInfo.NAME, "11111");
        userMapper.selectByCriteria(simpleModelCriteria);

        userMapper.select2();
    }
}
