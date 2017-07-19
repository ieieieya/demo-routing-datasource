package com.lichenxing.routingdatasource.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.math.BigInteger;
import java.util.Map;

/**
 * MultipleDataSource
 *
 * @author Chenxing Li
 * @date 18/07/2017 21:11
 */

public class MultipleDataSource extends AbstractRoutingDataSource {

    private ThreadLocal<Integer> dataSourceKey = new ThreadLocal<>();

    public MultipleDataSource(Object defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super();
        setDefaultTargetDataSource(defaultTargetDataSource);
        setTargetDataSources(targetDataSources);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Integer key = dataSourceKey.get();
        if (key == null) {
            return null;
        }
        // TODO 这里的3是需要搞成自动配置的
        return key % 3;
    }

    public void setDataSourceKey(Integer tenantId) {
        this.dataSourceKey.set(tenantId);
    }
}
