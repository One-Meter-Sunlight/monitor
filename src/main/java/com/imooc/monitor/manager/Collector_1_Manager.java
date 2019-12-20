package com.imooc.monitor.manager;

import com.imooc.monitor.datasource.DynamicDataSourceContextHolder;
import com.imooc.monitor.service.CollectorAdlinkService;
import com.imooc.monitor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 类型为1的采集器接口
 *
 * @author kai.chen
 * @date 2019-12-20
 */
@Slf4j
@Service
public class Collector_1_Manager {

    @Resource
    private UserService userService;
    @Resource
    private CollectorAdlinkService collectorAdlinkService;

    public void queryAndSaveCollectorData() {
        DynamicDataSourceContextHolder.setDataSource("center");
        getUserList();
        DynamicDataSourceContextHolder.setDataSource("test");
        getByType();
    }

    private void getUserList() {
        userService.list();
    }

    private void getByType() {
        collectorAdlinkService.getListByType("1");
    }
}
