package com.sr.om.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties(prefix = "database.hikari")
public class WriteHikari extends HikariConfig {

    @Value("${database.packagesToScan}")
    private String packagesToScan;

    @Bean(name = "write")
    public DataSource dataSource() {
        return new HikariDataSource(this);
    }

}
