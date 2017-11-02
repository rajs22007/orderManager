package com.sr.om.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DatabaseConfig {

    @Inject
    private MultiDataSourceConfig multiDataSourceConfig;

    @Value("${database.packagesToScan}")
    private String packagesToScan;

    @Inject
    private HibernateConfig hibernateConfig;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(multiDataSourceConfig.routingDataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] { packagesToScan });
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties hibernateProperties = new Properties();
        hibernateProperties.putAll(hibernateConfig.getHibernateProperties());
        entityManagerFactoryBean.setJpaProperties(hibernateProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws PropertyVetoException {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

        return new PersistenceExceptionTranslationPostProcessor();
    }

}
