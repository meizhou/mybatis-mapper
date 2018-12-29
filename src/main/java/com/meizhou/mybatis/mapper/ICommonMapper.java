package com.meizhou.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICommonMapper<T>  {

	Integer insertSelective(@Param("record") T t);

	Integer insertSelectiveOrUpdate(@Param("record") T t);

	Integer deleteByPrimaryKey(@Param("id") Object id);

	Integer deleteByCriteria(@Param("criterias") ModelCriteria criteria);

	T getByPrimaryKey(@Param("id") Object id);

	List<T> selectByCriteria(@Param("criterias") ModelCriteria criteria);

	Integer countByCriteria(@Param("criterias") ModelCriteria criteria);

	Integer updateSelective(@Param("record") T t);

	Integer updateSelectiveWithOperations(@Param("record") T t, @Param("operations") List<SqlOperation> operationList);

	Integer updateByCriteriaSelective(@Param("record") T t, @Param("criterias") ModelCriteria criteria);

	Integer updateSelectiveWithOperationsByWhere(@Param("record") T t, @Param("where") T where, @Param("operations") List<SqlOperation> operationList);

	Integer updateSelectiveWithOperationsByCriteria(@Param("record") T t, @Param("operations") List<SqlOperation> operationList, @Param("criterias") ModelCriteria criteria);


}
