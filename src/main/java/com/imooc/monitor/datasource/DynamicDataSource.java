package com.imooc.monitor.datasource;

import com.imooc.monitor.manager.Collector_1_Manager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DynamicDataSourceContextHolder.getDataSource();
        if (dataSource == null) {
            logger.info("当前数据源为[center]");
        } else {
            logger.info("当前数据源为{}", dataSource);
        }
        return dataSource;
    }
}
