package com.meizhou.mybatis.mapper;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.visitor.ParameterizedOutputVisitorUtils;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meizhou on 2018/8/19.
 */
public class SqlPasterUpdate {

    public static void main(String[] args) {
        String sql = SQLUtils.format("update t set `val` = 12312 where id = 2 and name = 'wenshao'", JdbcConstants.MYSQL);
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        StringBuffer where = new StringBuffer();
        SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(JdbcConstants.MYSQL);
        for (SQLStatement stmt : stmtList) {
            stmt.accept(statVisitor);
        }

        List<Object> objectList = new ArrayList();
        System.out.println(ParameterizedOutputVisitorUtils.parameterize(sql, JdbcConstants.MYSQL, objectList));
        System.out.println(objectList);

        System.out.println(statVisitor.getParameters());
        System.out.println(statVisitor.getColumns());
        System.out.println(statVisitor.getTables());
        System.out.println(statVisitor.getConditions());
    }
}
