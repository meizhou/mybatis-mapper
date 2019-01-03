<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapperName}">

    <sql id="Division_Table_Name">
    ${r"${"}tableName}
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

    <sql id="Criteria_Where_Clause">
        <where>
            <foreach collection="criterias.oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" suffix=")" prefixOverrides="and">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${r"${"}criterion.condition}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${r"${"}criterion.condition} ${r"#{"}criterion.value}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${r"${"}criterion.condition} ${r"#{"}criterion.value} and ${r"#{"}
                                    criterion.secondValue}
                                </when>
                                <when test="criterion.listValue">
                                    and ${r"${"}criterion.condition}
                                    <foreach collection="criterion.value" item="listItem" open="(" close=")"
                                             separator=",">
                                    ${r"#{"}listItem}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>

    <sql id="Update_By_Criteria_Where_Clause">
        <where>
            <foreach collection="criterias.oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" suffix=")" prefixOverrides="and">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${r"${"}criterion.condition}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${r"${"}criterion.condition} ${r"#{"}criterion.value}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${r"${"}criterion.condition} ${r"#{"}criterion.value} and ${r"#{"}
                                    criterion.secondValue}
                                </when>
                                <when test="criterion.listValue">
                                    and ${r"${"}criterion.condition}
                                    <foreach collection="criterion.value" item="listItem" open="(" close=")"
                                             separator=",">
                                    ${r"#{"}listItem}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>

    <select id="get" parameterType="${pojoClazzName}" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="Division_Table_Name"/>
        where 1=1
        <#list properties as property>
            <if test="record.${property.name} != null">
                and `${property.column}` = ${r"#{"}record.${property.name},jdbcType=${property.jdbcType}}
            </if>
        </#list>
    </select>

    <delete id="delete">
        delete from
        <include refid="Division_Table_Name"/>
        where 1=1
        <#list properties as property>
            <if test="record.${property.name} != null">
                and `${property.column}` = ${r"#{"}record.${property.name},jdbcType=${property.jdbcType}}
            </if>
        </#list>
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
        and ${shardKey} = ${r"#{"}record.${shardKeyColumn},jdbcType=${shardKeyColumnJDBCType}}
    </update>

    <select id="select" resultMap="BaseResultMap" parameterType="${pojoClazzName}">
        select
        <if test="criterias.distinct">
            distinct
        </if>
        <include refid="Base_Column_List"/>
        from
        <include refid="Division_Table_Name"/>
        <if test="criterias != null">
            <include refid="Criteria_Where_Clause"/>
        </if>
        <if test="criterias.orderByClause != null">
            order by ${r"${"}criterias.orderByClause}
        </if>
        <if test="criterias.limit != 10000">
            limit ${r"${"}criterias.offset},${r"${"}criterias.limit}
        </if>
        <if test="criterias.forUpdate == true">
            for update
        </if>
    </select>

    <update id="updateByCriteriaSelective" parameterType="map">
        update
        <include refid="Division_Table_Name"/>
        <set>
        <#list properties as property>
            <if test="record.${property.name} != null and '${primaryKeyColumn}' != '${property.column}'">
            `${property.column}` = ${r"#{"}record.${property.name},jdbcType=${property.jdbcType}},
            </if>
        </#list>
        </set>
        <if test="criterias != null">
            <include refid="Update_By_Criteria_Where_Clause"/>
        </if>
        and ${shardKey} = ${r"#{"}record.${shardKeyColumn},jdbcType=${shardKeyColumnJDBCType}}
    </update>

    <select id="countByCriteria" parameterType="map" resultType="java.lang.Integer" useCache="true">
        select count(*) from
        <include refid="Division_Table_Name"/>
        <if test="_parameter != null">
            <include refid="Criteria_Where_Clause"/>
        </if>
    </select>
    
    <update id="updateSelectiveWithOperationsByCriteria" parameterType="map">
        update
        <include refid="Division_Table_Name"/>
        <set>
        <#list properties as property>
            <if test="record.${property.name} != null and '${primaryKeyColumn}' != '${property.column}'">
            `${property.column}` = ${r"#{"}record.${property.name},jdbcType=${property.jdbcType}},
            </if>
        </#list>
            <foreach collection="operations" item="operation" separator=",">
                `${r"${"}operation.opercolumn}` = `${r"${"}operation.opercolumn}` ${r"${"}
                operation.sqlOperator.operator} ${r"${"}operation.operand}
            </foreach>
        </set>
        <if test="criterias != null">
            <include refid="Update_By_Criteria_Where_Clause"/>
        </if>
        and ${shardKey} = ${r"#{"}record.${shardKeyColumn},jdbcType=${shardKeyColumnJDBCType}}
    </update>

</mapper>