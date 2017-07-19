package com.lichenxing.routingdatasource.conf;

import com.lichenxing.routingdatasource.routing.domain.RoutingChatMessage;
import com.lichenxing.routingdatasource.routing.jpa.RoutingChatMessageRepository;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * ChatMessagePersistenceConfig
 *
 * @author Chenxing Li
 * @date 18/07/2017 23:39
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "routingEntityManagerFactory",
        transactionManagerRef = "routingTransactionManager",
        basePackageClasses = RoutingChatMessageRepository.class)
public class RoutingPersistenceConfig {

    @Bean
    public PlatformTransactionManager routingTransactionManager(EntityManagerFactory routingEntityManagerFactory) {
        return new JpaTransactionManager(routingEntityManagerFactory);
    }

    @Bean
    @ConfigurationProperties(prefix = "routing.spring.jpa")
    public JpaProperties routingJpaProperties() {
        return new JpaProperties();
    }

    @Bean
    public JpaVendorAdapter routingJpaVendorAdapter(JpaProperties routingJpaProperties) {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(routingJpaProperties.isShowSql());
        jpaVendorAdapter.setDatabase(routingJpaProperties.getDatabase());
        jpaVendorAdapter.setDatabasePlatform(routingJpaProperties.getDatabasePlatform());
        jpaVendorAdapter.setGenerateDdl(routingJpaProperties.isGenerateDdl());
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean routingEntityManagerFactory(JpaVendorAdapter routingJpaVendorAdapter, JpaProperties routingJpaProperties, DataSource routingDataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(routingJpaVendorAdapter);
        entityManagerFactoryBean.setDataSource(routingDataSource);
        entityManagerFactoryBean.setPackagesToScan(RoutingChatMessage.class.getPackage().getName());
        entityManagerFactoryBean.getJpaPropertyMap().putAll(routingJpaProperties.getProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    public JdbcTemplate routingJdbcTemplate(DataSource routingDataSource) {
        return new JdbcTemplate(routingDataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate routingNamedParameterJdbcTemplate(DataSource routingDataSource) {
        return new NamedParameterJdbcTemplate(routingDataSource);
    }

}
