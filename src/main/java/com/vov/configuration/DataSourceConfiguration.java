package com.vov.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfiguration {

  @Bean
  public DataSource getDataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setAutoCommit(false);
    dataSource.setPoolName("H2PoolConnection");
    dataSource.setJdbcUrl("jdbc:h2:./h2/database;JMX=TRUE;AUTO_RECONNECT=TRUE;WRITE_DELAY=0;AUTO_SERVER=TRUE");
    dataSource.setUsername("admin");
    dataSource.setPassword("admin");
    dataSource.setAutoCommit(true);
    dataSource.setMinimumIdle(10);
    dataSource.setMaximumPoolSize(40);
    dataSource.setIdleTimeout(10000);
    dataSource.setMaxLifetime(30000);
    dataSource.setReadOnly(false);
    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);
    vendorAdapter.setShowSql(false);
    vendorAdapter.setPrepareConnection(true);
    vendorAdapter.setDatabase(Database.H2);
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.vov.model");
    factory.setDataSource(getDataSource());
    factory.setJpaProperties(additionalProperties());
    return factory;
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);
    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(getDataSource());
  }

  Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

    return properties;
  }

}

