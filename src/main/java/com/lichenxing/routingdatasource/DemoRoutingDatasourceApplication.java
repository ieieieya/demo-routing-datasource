package com.lichenxing.routingdatasource;

import com.lichenxing.routingdatasource.annotation.EnableShard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
		exclude = {
				DataSourceAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class,
				JdbcTemplateAutoConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class,
				TransactionAutoConfiguration.class
		})
@EnableShard
@EnableDiscoveryClient
public class DemoRoutingDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRoutingDatasourceApplication.class, args);
	}

}
