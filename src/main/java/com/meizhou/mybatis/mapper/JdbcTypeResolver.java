/**
 * 
 */
package com.meizhou.mybatis.mapper;

import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hejun
 * JavaType映射JdbcType
 *
 */
public class JdbcTypeResolver {
	private static Map<Class<?>, JdbcType> clazzToJdbcTypeMap = new HashMap<Class<?>, JdbcType>();
	
	static {
		clazzToJdbcTypeMap.put(Integer.class, JdbcType.INTEGER);
		clazzToJdbcTypeMap.put(String.class, JdbcType.VARCHAR);
		clazzToJdbcTypeMap.put(Long.class, JdbcType.BIGINT);
		clazzToJdbcTypeMap.put(Date.class, JdbcType.TIMESTAMP);
		clazzToJdbcTypeMap.put(Byte.class, JdbcType.TINYINT);
		clazzToJdbcTypeMap.put(Short.class, JdbcType.SMALLINT);
		clazzToJdbcTypeMap.put(Double.class, JdbcType.DECIMAL);
		clazzToJdbcTypeMap.put(Boolean.class, JdbcType.TINYINT);
		clazzToJdbcTypeMap.put(BigDecimal.class, JdbcType.DECIMAL);
	}
	
	public static JdbcType getJdbcType(Class<?> clazz) {
		return clazzToJdbcTypeMap.get(clazz);
	}
	
	public static void main(String[] args) {
		System.out.println(JdbcTypeResolver.getJdbcType(Integer.class));
	}
}
