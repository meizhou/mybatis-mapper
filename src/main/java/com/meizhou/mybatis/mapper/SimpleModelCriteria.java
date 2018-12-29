package com.meizhou.mybatis.mapper;


import java.util.ArrayList;
import java.util.List;

/**
 * @author administrator
 */
public class SimpleModelCriteria extends ModelCriteria {

    private List<String> orderList = new ArrayList<>();

    public SimpleModelCriteria() {
        super();
    }

    private ModelCriteria.Criteria criteria = super.createCriteria();

    public SimpleModelCriteria eq(String propertyName, Object value) {
        criteria.addCriterion(" `" + CamelCaseUtils.toUnderlineName(propertyName) + "` = ", value, CamelCaseUtils.toUnderlineName(propertyName));
        return this;
    }

    public SimpleModelCriteria neq(String propertyName, Object value) {
        criteria.addCriterion(" `" + CamelCaseUtils.toUnderlineName(propertyName) + "` != ", value, CamelCaseUtils.toUnderlineName(propertyName));
        return this;
    }

    public SimpleModelCriteria in(String propertyName, List<?> list) {
        criteria.addCriterion(" `" + CamelCaseUtils.toUnderlineName(propertyName) + "` in ", list, CamelCaseUtils.toUnderlineName(propertyName));
        return this;
    }

    // 小于
    public SimpleModelCriteria lt(String propertyName, Number value) {
        criteria.addCriterion(" `" + CamelCaseUtils.toUnderlineName(propertyName) + "` < ", value, CamelCaseUtils.toUnderlineName(propertyName));
        return this;
    }

    // 小于等于
    public SimpleModelCriteria le(String propertyName, Number value) {
        criteria.addCriterion(" `" + CamelCaseUtils.toUnderlineName(propertyName) + "` <= ", value, CamelCaseUtils.toUnderlineName(propertyName));
        return this;
    }

    // 大于
    public SimpleModelCriteria gt(String propertyName, Number value) {
        criteria.addCriterion(" `" + CamelCaseUtils.toUnderlineName(propertyName) + "` > ", value, CamelCaseUtils.toUnderlineName(propertyName));
        return this;
    }

    // 大于等于
    public SimpleModelCriteria ge(String propertyName, Number value) {
        criteria.addCriterion(" `" + CamelCaseUtils.toUnderlineName(propertyName) + "` >= ", value, CamelCaseUtils.toUnderlineName(propertyName));
        return this;
    }

    //
    public SimpleModelCriteria orderDesc(String column) {

        orderList.add(" `" + CamelCaseUtils.toUnderlineName(column) + "` desc");
        super.setOrderByClause(getOrder());
        return this;
    }

    public SimpleModelCriteria orderAsc(String column) {
        orderList.add(" `" + CamelCaseUtils.toUnderlineName(column) + "` asc ");
        super.setOrderByClause(getOrder());
        return this;
    }

    private String getOrder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < orderList.size(); i++) {
            if (0 == i) {
                sb.append(" " + orderList.get(i).toString() + " ");
            } else {
                sb.append("," + orderList.get(i).toString() + " ");
            }
        }
        return sb.toString();
    }

    public SimpleModelCriteria pageInfo(PageInfo pageInfo) {
        if (pageInfo != null) {
            limit(pageInfo.getLimit());
            offset(pageInfo.getOffset());
        }
        return this;
    }

    public SimpleModelCriteria limit(int limit) {
        super.setLimit(limit);
        return this;
    }

    public SimpleModelCriteria offset(int start) {
        super.setOffset(start);
        return this;
    }

    public SimpleModelCriteria orCri() {
        criteria = super.or();
        return this;
    }
}