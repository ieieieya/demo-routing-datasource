package com.lichenxing.routingdatasource.conf;

import com.lichenxing.routingdatasource.datasource.MultipleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

/**
 * RoutingDataSourceConfiguration
 *
 * @author Chenxing Li
 * @date 01/08/2017 18:13
 */
@Configuration
public class RoutingDataSourceConfiguration {


    private List<DataSource> buildRoutingDataSource(String[] urlArr, String[] usernameArr, String[] passwordArr, String driverClassName) {
        if (urlArr == null || usernameArr == null || passwordArr == null) {
            throw new IllegalArgumentException("Error building routing datasource, params can not be null.");
        }
        if (urlArr.length != usernameArr.length || urlArr.length != passwordArr.length) {
            throw new IllegalArgumentException("Error building routing datasource, params wrong.");
        }
        List<DataSource> dataSourceList = new LinkedList<>();
        for (int i = 0; i < urlArr.length; i++) {
            DataSourceProperties dataSourceProperties = new DataSourceProperties();
            dataSourceProperties.setUrl(urlArr[i]);
            dataSourceProperties.setUsername(usernameArr[i]);
            dataSourceProperties.setPassword(passwordArr[i]);
            dataSourceProperties.setDriverClassName(driverClassName);
            dataSourceList.add(dataSourceProperties.initializeDataSourceBuilder().build());
        }
        return dataSourceList;
    }

    @Value("#{'${routing-write.spring.datasource.urls}'.split(',')}")
    private String[] writeUrlArr;
    @Value("#{'${routing-write.spring.datasource.usernames}'.split(',')}")
    private String[] writeUsernameArr;
    @Value("#{'${routing-write.spring.datasource.passwords}'.split(',')}")
    private String[] writePasswordArr;
    @Value("${routing.spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource routingDataSource(DataSource dataSource) {
        List<DataSource> writeDataSourceList = buildRoutingDataSource(writeUrlArr, writeUsernameArr, writePasswordArr, driverClassName);
        return new MultipleDataSource(3, dataSource, writeDataSourceList, null);
    }

}
