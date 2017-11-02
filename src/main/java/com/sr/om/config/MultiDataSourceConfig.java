package com.sr.om.config;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sr.om.enums.DataSourceType;

@Configuration
public class MultiDataSourceConfig {

    @Inject
    private ReadHikari readHikari;

    @Inject
    private WriteHikari writeHikari;

    @Bean(name = "route")
    public RoutingDataSource routingDataSource() {
        RoutingDataSource dataSource = new RoutingDataSource();
        dataSource.setDefaultTargetDataSource(writeHikari.dataSource());
        Map<Object, Object> dbMap = new HashMap<Object, Object>();
        dbMap.put(DataSourceType.WRITE, writeHikari.dataSource());
        dbMap.put(DataSourceType.READ, readHikari.dataSource());
        dataSource.setTargetDataSources(dbMap);
        return dataSource;
    }

}
