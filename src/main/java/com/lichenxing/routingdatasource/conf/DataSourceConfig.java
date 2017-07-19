package com.lichenxing.routingdatasource.conf;

import com.lichenxing.routingdatasource.datasource.MultipleDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * DataSourceConfig
 *
 * @author Chenxing Li
 * @date 18/07/2017 21:22
 */
@Configuration
public class DataSourceConfig {

    @Bean
//    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
//    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("routing-1.spring.datasource")
    public DataSourceProperties routing1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("routing-1.spring.datasource")
    public DataSource routing1DataSource() {
        return routing1DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("routing-2.spring.datasource")
    public DataSourceProperties routing2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("routing-2.spring.datasource")
    public DataSource routing2DataSource() {
        return routing2DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("routing-3.spring.datasource")
    public DataSourceProperties routing3DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("routing-3.spring.datasource")
    public DataSource routing3DataSource() {
        return routing3DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSource routingDataSource(DataSource dataSource, DataSource routing1DataSource, DataSource routing2DataSource, DataSource routing3DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        // 必须设置上defaultTargetSource，否则没有routingKey的时候会初始化失败
        // 给不同的routingKey设置数据源
        // TODO 这里应该可以自动生成，而不是写死
        targetDataSources.put(0, routing1DataSource);
        targetDataSources.put(1, routing2DataSource);
        targetDataSources.put(2, routing3DataSource);
        return new MultipleDataSource(dataSource, targetDataSources);
    }


}
