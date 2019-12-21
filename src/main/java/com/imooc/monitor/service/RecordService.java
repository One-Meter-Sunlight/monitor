package com.imooc.monitor.service;

import com.baomidou.mybatisplus.service.IService;
import com.imooc.monitor.entity.Record;

import java.util.List;

/**
 * 采集器数据管理服务类
 *
 * @author kai.chen
 * @since 2019-06-23
 */
public interface RecordService extends IService<Record> {

    /**
     * 通过表面后缀保存相关采集器数据
     *
     * @param recordList
     * @param collectorId
     */
    void addBatchByTableNameSuffix(List<Record> recordList, String collectorId);
}
