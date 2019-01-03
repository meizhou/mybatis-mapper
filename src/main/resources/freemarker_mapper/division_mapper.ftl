<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapperName}">

    <sql id="Division_Table_Name">
    ${tableName}<#if (modelSize>1)>_${r"${"}mod}</#if>
    </sql>

    <resultMap id="BaseResultMap" type="${pojoClazzName}">
    <#list properties as property>
        <#if property.name == "id">
            <id column="id" property="id" jdbcType="INTEGER"/>
        <#else>
            <result column="${property.column}" property="${property.name}" jdbcType="${property.jdbcType}"/>
        </#if>
    </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <trim prefix="" suffix="" suffixOverrides=",">
        <#list properties as property>`${property.column}`,</#list>
        </trim>
    </sql>

    <select id="getByPrimaryKey" resultMap="BaseResultMap" useCache="true">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="Division_Table_Name"/>
        where ${primaryKey} = ${r"#{"}id,jdbcType=${primaryKeyColumnJDBCType}}
    </select>

    <delete id="deleteByPrimaryKey">
        delete from
        <include refid="Division_Table_Name"/>
        where ${primaryKey} = ${r"#{"}id,jdbcType=${primaryKeyColumnJDBCType}}
    </delete>

    <insert id="insertSelective" parameterType="${pojoClazzName}" useGeneratedKeys="true"
            keyProperty="record.${primaryKeyColumn}">
        insert into
        <include refid="Division_Table_Name"/>
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list properties as property>
            <if test="record.${property.name} != null">
                `${property.column}`,
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list properties as property>
            <if test="record.${property.name} != null and '${property.column}' != 'created_at'">
            ${r"#{"}record.${property.name},jdbcType=${property.jdbcType}},
            </if>
            <if test="record.${property.name} != null and '${property.column}' == 'created_at'">
                UNIX_TIMESTAMP(),
            </if>
        </#list>
        </trim>
    </insert>

    <insert id="insertSelectiveOrUpdate" parameterType="${pojoClazzName}" useGeneratedKeys="true"
            keyProperty="record.${primaryKeyColumn}">
        insert into
        <include refid="Division_Table_Name"/>
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list properties as property>
            <if test="record.${property.name} != null">
                `${property.column}`,
            </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list properties as property>
            <if test="record.${property.name} != null and '${property.column}' != 'created_at'">
            ${r"#{"}record.${property.name},jdbcType=${property.jdbcType}},
            </if>
            <if test="record.${property.name} != null and '${property.column}' == 'created_at'">
                UNIX_TIMESTAMP(),
            </if>
        </#list>
        </trim>
        <trim prefix="on duplicate key update " suffix="" suffixOverrides=",">
        <#list properties as property>
            <if test="record.${property.name} != null and '${property.column}' != 'created_at'">
                `${property.column}` = ${r"#{"}record.${property.name},jdbcType=${property.jdbcType}},
            </if>
        </#list>
        </trim>
    </insert>

    <update id="updateSelective" parameterType="${pojoClazzName}">
        update
        <include refid="Division_Table_Name"/>
        <set>
        <#list properties as property>
            <if test="record.${property.name} != null and '${primaryKeyColumn}' != '${property.name}'">
                `${property.column}` = ${r"#{"}record.${property.name},jdbcType=${property.jdbcType}},
            </if>
        </#list>
        </set>
        where ${primaryKey} = ${r"#{"}record.${primaryKeyColumn},jdbcType=${primaryKeyColumnJDBCType}}
    </update>

    <update id="updateSelectiveWithOperations" parameterType="${pojoClazzName}">
        update
        <include refid="Division_Table_Name"/>
        <set>
        <#list properties as property>
            <if test="record.${property.name} != null and '${primaryKeyColumn}' != '${property.name}'">
                `${property.column}` = ${r"#{"}record.${property.name},jdbcType=${property.jdbcType}},
            </if>
        </#list>
            <foreach collection="operations" item="operation" separator=",">
                `${r"${"}operation.opercolumn}` = `${r"${"}operation.opercolumn}` ${r"${"}
                operation.sqlOperator.operator} ${r"${"}operation.operand}
            </foreach>
        </set>
        where ${primaryKey} = ${r"#{"}record.${primaryKeyColumn},jdbcType=${primaryKeyColumnJDBCType}}
    </update>


    <update id="updateSelectiveWithOperationsByWhere" parameterType="${pojoClazzName}">
        update
        <include refid="Division_Table_Name"/>
        <set>
        <#list properties as property>
            <if test="record.${property.name} != null and '${primaryKeyColumn}' != '${property.name}'">
                `${property.column}` = ${r"#{"}record.${property.name},jdbcType=${property.jdbcType}},
            </if>
        </#list>
            <foreach collection="operations" item="operation" separator=",">
                `${r"${"}operation.opercolumn}` = `${r"${"}operation.opercolumn}` ${r"${"}
                operation.sqlOperator.operator} ${r"${"}operation.operand}
            </foreach>
        </set>
        where 1=1
        <#list properties as property>
            <if test="where.${property.name} != null">
                and `${property.column}` = ${r"#{"}where.${property.name},jdbcType=${property.jdbcType}}
            </if>
        </#list>
    </update>

</mapper>