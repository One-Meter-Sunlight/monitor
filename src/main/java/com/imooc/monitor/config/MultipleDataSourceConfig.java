package com.imooc.monitor.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.imooc.monitor.datasource.DynamicDataSource;
import com.imooc.monitor.datasource.DynamicDataSourceContextHolder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据源配置
 */
@Configuration
public class MultipleDataSourceConfig {

    @Bean(name = "centerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.center")
    public DataSource centerDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "testDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean
    public DataSource dynamicDataSource(@Qualifier("centerDataSource") DataSource centerDataSource,
                                        @Qualifier("testDataSource") DataSource testDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        // 设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(centerDataSource);

        // 设置多数据源
        Map<Object, Object> dataSourceMap = new ConcurrentHashMap<>();
        dataSourceMap.put("center", centerDataSource);
        dataSourceMap.put("test", testDataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        // 将数据源标识加入到列表中
        DynamicDataSourceContextHolder.getDataSourceIds().add("center");
        DynamicDataSourceContextHolder.getDataSourceIds().add("test");

        return dynamicDataSource;
    }

}
