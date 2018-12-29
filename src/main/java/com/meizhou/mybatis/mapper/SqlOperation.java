package com.meizhou.mybatis.mapper;

import com.meizhou.framework.utils.CamelCaseUtils;

/**
 * Created by hejun on 2015/4/24.
 */
public class SqlOperation {
    private String opercolumn;
    private SqlOperator sqlOperator;
    private Object operand;

    private SqlOperation(String opercolumn, SqlOperator sqlOperator, Object operand) {
        this.opercolumn = opercolumn;
        this.sqlOperator = sqlOperator;
        this.operand = operand;
    }

    public static final SqlOperation getAndOperation(String opercolumn, Object operand) {
        return new SqlOperation(CamelCaseUtils.toUnderlineName(opercolumn), SqlOperator.AND, operand);
    }

    public static final SqlOperation getOrOperation(String opercolumn, Object operand) {
        return new SqlOperation(CamelCaseUtils.toUnderlineName(opercolumn), SqlOperator.OR, operand);
    }

    public static final SqlOperation getMinusOperation(String opercolumn, Object operand) {
        return new SqlOperation(CamelCaseUtils.toUnderlineName(opercolumn), SqlOperator.MINUS, operand);
    }

    public static final SqlOperation getPlusOperation(String opercolumn, Object operand) {
        return new SqlOperation(CamelCaseUtils.toUnderlineName(opercolumn), SqlOperator.PLUS, operand);
    }

    public String getOpercolumn() {
        return opercolumn;
    }

    public void setOpercolumn(String opercolumn) {
        this.opercolumn = opercolumn;
    }

    public SqlOperator getSqlOperator() {
        return sqlOperator;
    }

    public void setSqlOperator(SqlOperator sqlOperator) {
        this.sqlOperator = sqlOperator;
    }

    public Object getOperand() {
        return operand;
    }

    public void setOperand(Object operand) {
        this.operand = operand;
    }
}
