package com.imooc.monitor.task;

import com.imooc.monitor.manager.Collector_1_Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 类型为1的采集器定时任务
 */
@Component
@Slf4j
public class CollectorDataTask_1 {

    @Resource
    private Collector_1_Manager collector_1_manager;

    /**
     * c_0001 采集器定时任务，每小时执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    private void type_1_task() {
        collector_1_manager.queryAndSaveCollectorData();
    }

}
