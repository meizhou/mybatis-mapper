package com.meizhou.mybatis.mapper;

import com.meizhou.framework.spring.PropertiesUtils;
import com.meizhou.framework.utils.CamelCaseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hejun 由实体POJO生成的Meta对象
 */
public class MapperMeta {

	private static final Logger LOGGER = LoggerFactory.getLogger(MapperMeta.class);

	private Class<?> pojoClazz;

	private String pojoClazzName;

	private List<POJOProperty> properties = new ArrayList<POJOProperty>();

	private String tableName;

	private String mapperName;

	private Integer modelSize;

	private String primaryKey;

	private String primaryKeyColumn;

	private JdbcType primaryKeyColumnJDBCType;

	private String shardKey;

	private String shardKeyColumn;

	private JdbcType shardKeyColumnJDBCType;

	public MapperMeta(Class<?> pojoClazz) throws Exception {
		super();
		this.pojoClazz = pojoClazz;
		this.pojoClazzName = pojoClazz.getName();
		for (Field field : this.pojoClazz.getDeclaredFields()) {
			if (!Modifier.isStatic(field.getModifiers())) {
				POJOProperty pojoProperty = new POJOProperty(field.getName(), field.getType());
				properties.add(pojoProperty);
			}
		}
		String className = pojoClazz.getName();
		String nopackageClassName = className.substring(className.lastIndexOf(".") + 1);
		tableName = CamelCaseUtils.toUnderlineName(nopackageClassName);
		String modelSizeStr = PropertiesUtils.getProperty(tableName + ".table.size");
		modelSize = 1;
		if (StringUtils.isNotBlank(modelSizeStr)) {
			try {
				modelSize = Integer.parseInt(modelSizeStr);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				modelSize = 1;
			}
		}
		String poPackageName = className.substring(0, className.lastIndexOf("."));
		String basePackageName = poPackageName.substring(0, poPackageName.lastIndexOf("."));
		mapperName = basePackageName + ".mapper" + "." + "I" + nopackageClassName + MybatisConstants.MAPPER_SUFFIX;
		if (poPackageName.endsWith(".model.po")) {
			basePackageName = poPackageName.substring(0, poPackageName.lastIndexOf(".model.po"));
			mapperName = basePackageName + ".mapper" + "." + "I" + nopackageClassName + MybatisConstants.MAPPER_SUFFIX;
		}
		primaryKeyColumn = ClassUtils.getPrimaryKeyName(pojoClazz);
		primaryKey = CamelCaseUtils.toUnderlineName(primaryKeyColumn);
		primaryKeyColumnJDBCType = JdbcTypeResolver.getJdbcType(ClassUtils.getPrimaryKeyType(pojoClazz));
		shardKeyColumn = ClassUtils.getShardKeyName(pojoClazz);
		if (null != shardKeyColumn) {
			shardKey = CamelCaseUtils.toUnderlineName(shardKeyColumn);
			shardKeyColumnJDBCType = JdbcTypeResolver.getJdbcType(ClassUtils.getShardKeyType(pojoClazz));
		}
	}

	public String getShardKey() {
		return shardKey;
	}

	public void setShardKey(String shardKey) {
		this.shardKey = shardKey;
	}

	public String getShardKeyColumn() {
		return shardKeyColumn;
	}

	public void setShardKeyColumn(String shardKeyColumn) {
		this.shardKeyColumn = shardKeyColumn;
	}

	public JdbcType getShardKeyColumnJDBCType() {
		return shardKeyColumnJDBCType;
	}

	public void setShardKeyColumnJDBCType(JdbcType shardKeyColumnJDBCType) {
		this.shardKeyColumnJDBCType = shardKeyColumnJDBCType;
	}

	public Class<?> getPojoClazz() {
		return pojoClazz;
	}

	public void setPojoClazz(Class<?> pojoClazz) {
		this.pojoClazz = pojoClazz;
	}

	public List<POJOProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<POJOProperty> properties) {
		this.properties = properties;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getMapperName() {
		return mapperName;
	}

	public Integer getModelSize() {
		return modelSize;
	}

	public void setModelSize(Integer modelSize) {
		this.modelSize = modelSize;
	}

	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}

	public String getPojoClazzName() {
		return pojoClazzName;
	}

	public void setPojoClazzName(String pojoClazzName) {
		this.pojoClazzName = pojoClazzName;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getPrimaryKeyColumn() {
		return primaryKeyColumn;
	}

	public void setPrimaryKeyColumn(String primaryKeyColumn) {
		this.primaryKeyColumn = primaryKeyColumn;
	}

	public JdbcType getPrimaryKeyColumnJDBCType() {
		return primaryKeyColumnJDBCType;
	}

	public void setPrimaryKeyColumnJDBCType(JdbcType primaryKeyColumnJDBCType) {
		this.primaryKeyColumnJDBCType = primaryKeyColumnJDBCType;
	}
}