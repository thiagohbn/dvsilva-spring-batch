package com.springbatch.migracaodados.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

	/**
	 * Banco Primário - Spring Batch
	 *
	 * @return DataSource
	 */
	@Primary //Tem preferência sobre os beans gerenciados
	@Bean //Bean gerenciado pelo Spring
	@ConfigurationProperties(prefix = "spring.datasource") //application.properties
	public DataSource springDataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * Banco Secundário - Aplicação
	 *
	 * @return DataSource
	 */
	@Bean //Bean gerenciado pelo Spring
	@ConfigurationProperties(prefix = "app.datasource") //application.properties
	public DataSource appDataSource() {
		return DataSourceBuilder.create().build();
	}
}
