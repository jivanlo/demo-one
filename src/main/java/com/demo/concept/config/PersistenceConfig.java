package com.demo.concept.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by admin on 8/9/16.
 */
@Configuration
@ComponentScan(basePackages = "com.demo.concept")
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
@EnableJpaRepositories(basePackages = { "com.demo.concept.persistence.repository" })
@EnableTransactionManagement
public class PersistenceConfig
{

	@Bean(destroyMethod = "close")
	DataSource dataSource(final Environment env)
	{
		final HikariConfig dataSourceConfig = new HikariConfig();
		dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
		dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
		dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
		dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));

		return new HikariDataSource(dataSourceConfig);
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource, final Environment env)
	{

		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.demo.concept.persistence.entity");

		final Properties jpaProperties = new Properties();

		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
		jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory)
	{
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
