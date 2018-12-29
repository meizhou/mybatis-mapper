package com.meizhou.mybatis.mapper;

import com.meizhou.framework.spring.PropertiesUtils;

/**
 * Created by hejun on 2015/6/4.
 */
public class MybatisConstants {

	public static final String BASE_PACKAGE = PropertiesUtils.getProperty("root.package");

	public static final String POJO_PACKAGE = BASE_PACKAGE + ".model.po";

	public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".mapper";

	public static final String DAO_IMPL_PACKAGE = BASE_PACKAGE + ".dao.impl.";

	public static final String DAO_IMPL_SUFFIX = "DaoImpl";

	public static final String MAPPER_SUFFIX = "Mapper";

	public static final String DIVISION_MAPPER_TEMPLATE = "division_mapper.ftl";

	public static final String DIVISION_PRO_MAPPER_TEMPLATE = "division_pro_mapper.ftl";

	public static final String COMMON_MAPPER_TEMPLATE = "common_mapper.ftl";

}
