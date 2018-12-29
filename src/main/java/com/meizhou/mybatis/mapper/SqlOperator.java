package com.meizhou.mybatis.mapper;

/**
 * Created by hejun on 2015/4/24.
 */
public enum SqlOperator {
    AND("&"),
    OR("|"),
    MINUS("-"),
    PLUS("+");

    private String operator;

    SqlOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public static void main(String[] args) {
        System.out.println(SqlOperator.AND.getOperator());
    }
}
