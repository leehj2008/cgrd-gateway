package com.app;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	@Primary
	@Bean(name="mysql")
	@ConfigurationProperties(prefix="spring.mysql")
	public DataSource datasourceMySQL(){
		return DataSourceBuilder.create().build();
	}
	@Bean(name="oracle")
	@ConfigurationProperties(prefix="spring.oracle")
	public DataSource datasourceOracle(){
		return DataSourceBuilder.create().build();
	}

}
