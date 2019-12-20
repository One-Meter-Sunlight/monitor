package com.imooc.monitor.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源切换
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/11/28 21:38
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DynamicDataSourceContextHolder.getDataSource();
        if (dataSource == null) {
            log.info("当前数据源为[center]");
        } else {
            log.info("当前数据源为{}", dataSource);
        }
        return dataSource;
    }
}
