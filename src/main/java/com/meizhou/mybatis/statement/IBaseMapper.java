package com.meizhou.mybatis.statement;

import com.meizhou.mybatis.mapper.CriteriaExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by meizhou on 2018/9/15.
 */
public interface IBaseMapper<T> {

    int countByExample(CriteriaExample example);

    int deleteByExample(CriteriaExample example);

    int deleteByPrimaryKey(Object id);

    int insertSelective(T record);

    List<T> selectByExample(CriteriaExample example);

    T selectByPrimaryKey(Object id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") CriteriaExample example);

    int updateByExample(@Param("record") T record, @Param("example") CriteriaExample example);

    int updateByPrimaryKeySelective(T record);

}
