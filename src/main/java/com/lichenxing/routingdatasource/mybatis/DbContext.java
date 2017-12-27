package com.lichenxing.routingdatasource.mybatis;

import lombok.Data;

/**
 * DbContext
 *
 * @author Chenxing Li
 * @date 2017/12/27 14:44
 */
public class DbContext {

    private static ThreadLocal<String> dbIndexThreadLocal = new ThreadLocal<>();

    public static void setDbIndex(String dbIndex) {
        dbIndexThreadLocal.set(dbIndex);
    }

    public static String getDbIndex() {
        return dbIndexThreadLocal.get();
    }

    public static void clearContext() {
        dbIndexThreadLocal.remove();
    }

}
