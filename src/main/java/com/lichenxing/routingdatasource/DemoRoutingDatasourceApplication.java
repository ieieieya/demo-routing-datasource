package com.lichenxing.routingdatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;

@SpringBootApplication(
		exclude = {
				DataSourceAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class,
				JdbcTemplateAutoConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class,
				TransactionAutoConfiguration.class
		})
public class DemoRoutingDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRoutingDatasourceApplication.class, args);
	}

}
