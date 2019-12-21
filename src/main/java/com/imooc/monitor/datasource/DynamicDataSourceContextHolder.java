package com.imooc.monitor.datasource;

import com.imooc.monitor.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/11/28 21:39
 */
@Slf4j
public class DynamicDataSourceContextHolder {
    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);


    public static final ThreadLocal<String> contextHolder = new ThreadLocal();
    public static final List<String> dataSourceIds = new ArrayList();

    public static void setDataSource(String dataSource) {
        logger.info("切换到[{}]数据源", dataSource);
        contextHolder.set(dataSource);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        logger.info("释放当前数据源");
        contextHolder.remove();
    }

    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }

    public static List<String> getDataSourceIds() {
        return dataSourceIds;
    }
}
