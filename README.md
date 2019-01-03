
```
@MapperConfig(dataSource = "test")
```


```
<bean id="sqlSessionFactory" class="com.meizhou.mybatis.mapper.CommonMapperSqlSessionFactoryBean">
    <property name="dsName" value="test"/>
    <property name="basePackage" value="com.meizhou.fly"/>
    <property name="dataSource" ref="dataSource"/>
    <property name="configLocation" value="classpath:mybatis-config.xml"></property>
</bean>
```

```
public static void main(String[] args) throws Exception {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
    context.start();
    SqlSessionTemplate sqlSessionTemplate = context.getBean(SqlSessionTemplate.class);
    IUserInfoMapper userMapper = sqlSessionTemplate.getMapper(IUserInfoMapper.class);
    userMapper.getByPrimaryKey(1);
    userMapper.select2();
}
```
