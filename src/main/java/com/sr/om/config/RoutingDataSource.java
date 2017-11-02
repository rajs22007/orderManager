package com.sr.om.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // TODO Auto-generated method stub
        System.out.println("FFFFF======================" + DataSourceContextHolder.getDataSourceType());
        return DataSourceContextHolder.getDataSourceType();
    }
}
