package com.meizhou.mybatis.mapper;

import java.util.Stack;

/**
 * ${DESCRIPTION}
 *
 * @author shitou<shitou@in66.com>
 * @create 2016-11-10 16:55
 */

public class DataSourceReadMasterThreadLocal {

    private static ThreadLocal<Stack<Boolean>> holder = new ThreadLocal<Stack<Boolean>>();

    public static Boolean get() {
        return holder.get() != null && holder.get().size() > 0;
    }
    
    public static void add() {
        if (holder.get() == null) {
            holder.set(new Stack<Boolean>());
        }
        holder.get().add(true);
    }

    public static void pop() {
        holder.get().pop();
    }

}
