package com.imooc.monitor.task;

import com.alibaba.fastjson.JSON;
import com.imooc.monitor.manager.Collector_1_Manager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 类型为1的采集器定时任务
 */
@Component
@Slf4j
public class CollectorDataTask_1 {

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(Collector_1_Manager.class);

    @Resource
    private Collector_1_Manager collector_1_manager;

    /**
     * c_0001 采集器定时任务，每小时执行一次
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    private void type_1_task() {
        logger.info(">>>>>> 开始执行所有数据源采集器类型为1的数据保存定时任务，当前时间为：[{}] <<<<<<", JSON.toJSONStringWithDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
        collector_1_manager.queryAndSaveCollectorData();
    }

}
