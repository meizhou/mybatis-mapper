package com.meizhou.mybatis.mapper;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DataSourceReadMaster {

}
