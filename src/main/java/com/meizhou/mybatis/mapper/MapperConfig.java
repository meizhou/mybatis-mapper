package com.meizhou.mybatis.mapper;

/**
 * Created by hejun on 2015/4/28.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MapperConfig {

    String dataSource();

    int tableSize() default 1;

}
