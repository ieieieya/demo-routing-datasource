package com.lichenxing.routingdatasource.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MultipleDataSource
 *
 * @author Chenxing Li
 * @date 18/07/2017 21:11
 */

public class MultipleDataSource extends AbstractRoutingDataSource {

    private ThreadLocal<DataSourceKey> dataSourceKey = new ThreadLocal<>();
    private final Integer dbShardNum;

    public MultipleDataSource(Integer dbShardNum, Object defaultTargetDataSource, List<DataSource> writeDataSources, List<DataSource> readDataSources) {
        super();
        this.dbShardNum = dbShardNum;
        setDefaultTargetDataSource(defaultTargetDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        if (writeDataSources != null) {
            for (int i = 0; i < writeDataSources.size(); i++) {
                targetDataSources.put("w_" + i, writeDataSources.get(i));
            }
        }
        if (readDataSources != null) {
            for (int i = 0; i < readDataSources.size(); i++) {
                targetDataSources.put("r_" + i, readDataSources.get(i));
            }
        }
        setTargetDataSources(targetDataSources);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceKey key = dataSourceKey.get();
        if (key == null) {
            return null;
        }
        long remainder = key.getShardKey() % dbShardNum;
        return key.isReadOnly() ? "r_" + remainder : "w_" + remainder;
    }

    public void setDataSourceKey(boolean readOnly, long shardKey) {
        logger.debug("set current lookup key readOnly:" + readOnly + " shardKey:" + shardKey);
        this.dataSourceKey.set(new DataSourceKey(readOnly, shardKey));
    }


    @Override
    public Connection getConnection() throws SQLException {
        logger.debug("getting connection");
        Connection connection = super.getConnection();
        return connection;
    }

    public static class DataSourceKey {

        private boolean readOnly = false;

        private long shardKey;

        public DataSourceKey() {
            super();
        }

        public DataSourceKey(boolean readOnly, long shardKey) {
            super();
            this.readOnly = readOnly;
            this.shardKey = shardKey;
        }

        public boolean isReadOnly() {
            return readOnly;
        }

        public void setReadOnly(boolean readOnly) {
            this.readOnly = readOnly;
        }

        public long getShardKey() {
            return shardKey;
        }

        public void setShardKey(long shardKey) {
            this.shardKey = shardKey;
        }
    }

}

