package com.sr.om.config;

import com.sr.om.enums.DataSourceType;
import static com.google.common.base.Preconditions.checkNotNull;

public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<DataSourceType>();

    // private static DataSourceType name = DataSourceType.WRITE;

    public static void setDataSourceType(DataSourceType datasourceType) {

        contextHolder.set(checkNotNull(datasourceType));
        // name = datasourceType;

    }

    public static DataSourceType getDataSourceType() {
        return (DataSourceType) contextHolder.get();
        // return name;
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

}
