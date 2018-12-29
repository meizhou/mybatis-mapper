/**
 *
 */
package com.meizhou.mybatis.mapper;

import org.apache.ibatis.type.JdbcType;

/**
 * @author hejun
 *         POJO实体属性
 */
public class POJOProperty {
    private String name;
    private Class<?> type;
    private String column;
    private JdbcType jdbcType;

    public POJOProperty(String name, Class<?> type) {
        super();
        this.name = name;
        this.type = type;
        this.column = MybatisCamelCaseUtils.toUnderlineName(name);
        this.jdbcType = JdbcTypeResolver.getJdbcType(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(JdbcType jdbcType) {
        this.jdbcType = jdbcType;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{name=" + name + ",");
        sb.append("type=" + type.getName() + ",");
        sb.append("column=" + column + ",");
        sb.append("jdbcType=" + jdbcType + "}");
        return sb.toString();
    }

}
