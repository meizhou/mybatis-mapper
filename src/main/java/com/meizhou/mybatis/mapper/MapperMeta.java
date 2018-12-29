package com.meizhou.mybatis.mapper;

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

    public MapperMeta(Class<?> pojoClazz) {
        super();
        this.pojoClazz = pojoClazz;
        this.pojoClazzName = pojoClazz.getName();
        for (Field field : this.pojoClazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                POJOProperty pojoProperty = new POJOProperty(field.getName(), field.getType());
                properties.add(pojoProperty);
            }
        }
        MapperConfig mapperConfig = this.pojoClazz.getAnnotation(MapperConfig.class);
        tableName = MybatisClassUtils.getTableName(pojoClazz);
        modelSize = mapperConfig.tableSize();
        mapperName = MybatisClassUtils.getMapperClazzName(pojoClazz);
        primaryKeyColumn = MybatisClassUtils.getPrimaryKeyName(pojoClazz);
        primaryKey = MybatisCamelCaseUtils.toUnderlineName(primaryKeyColumn);
        primaryKeyColumnJDBCType = JdbcTypeResolver.getJdbcType(MybatisClassUtils.getPrimaryKeyType(pojoClazz));
        shardKeyColumn = MybatisClassUtils.getShardKeyName(pojoClazz);
        if (null != shardKeyColumn) {
            shardKey = MybatisCamelCaseUtils.toUnderlineName(shardKeyColumn);
            shardKeyColumnJDBCType = JdbcTypeResolver.getJdbcType(MybatisClassUtils.getShardKeyType(pojoClazz));
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
