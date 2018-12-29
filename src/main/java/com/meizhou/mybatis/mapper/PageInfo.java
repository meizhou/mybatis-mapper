package com.meizhou.mybatis.mapper;

/**
 * @author meizhou
 */
public class PageInfo {

    protected int offset;

    protected int limit;

    private PageInfo(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public static PageInfo buildByOffset(int offset, int limit) {
        PageInfo pageInfo = new PageInfo(offset, limit);
        return pageInfo;
    }

    public static PageInfo buildByPage(int page, int perPage) {
        if (page < 1) {
            page = 1;
        }
        PageInfo pageInfo = new PageInfo((page - 1) * perPage, perPage);
        return pageInfo;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

}
