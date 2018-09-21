package com.meizhou.fly.mapper;

import java.util.List;

/**
 * Created by meizhou on 2018/9/15.
 */
public interface IBaseMapper<T> {

    int insert();

    int updateById();

    int deleteById();

    List<T> select();

    T findById();

}
