package com.lichenxing.routingdatasource.conf;

import com.lichenxing.routingdatasource.domain.ChatMessage;
import com.lichenxing.routingdatasource.jpa.ChatMessageRepository;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * MainPersistenceConfig
 *
 * @author Chenxing Li
 * @date 18/07/2017 23:38
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackageClasses = ChatMessageRepository.class)
public class MainPersistenceConfig {

    @Bean
//    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
//    @Primary
    @ConfigurationProperties(prefix = "spring.jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Bean
//    @Primary
    public JpaVendorAdapter jpaVendorAdapter(JpaProperties jpaProperties) {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(jpaProperties.isShowSql());
        jpaVendorAdapter.setDatabase(jpaProperties.getDatabase());
        jpaVendorAdapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        jpaVendorAdapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        return jpaVendorAdapter;
    }

    @Bean
//    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter jpaVendorAdapter, JpaProperties jpaProperties, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(ChatMessage.class.getPackage().getName());
        entityManagerFactoryBean.getJpaPropertyMap().putAll(jpaProperties.getProperties());
        return entityManagerFactoryBean;
    }

}
