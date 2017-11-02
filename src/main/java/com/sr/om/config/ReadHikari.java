package com.sr.om.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties(prefix = "database2.hikari")
public class ReadHikari extends HikariConfig {

    @Value("${database2.packagesToScan}")
    private String packagesToScan;

    @Bean(name = "read")
    @Primary
    public DataSource dataSource() {
        return new HikariDataSource(this);
    }
}
