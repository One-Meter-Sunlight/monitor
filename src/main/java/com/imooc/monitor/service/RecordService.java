package com.imooc.monitor.service;

import com.baomidou.mybatisplus.service.IService;
import com.imooc.monitor.command.RecordCommand;
import com.imooc.monitor.entity.Record;
import com.imooc.monitor.vo.RecordVO;

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

    /**
     * 根据条件查询采集器数据记录
     *
     * @param command
     * @return
     */
    List<Record> listRecords(RecordCommand command, String collectorId);

    /**
     * 查询转换后的采集器记录列表
     *
     * @param command
     * @param collectorId
     * @return
     */
    List<RecordVO> listRecordsToTransfor(RecordCommand command, String collectorId);

    /**
     * 查询采集器通道数据记录
     *
     * @param collectorId
     * @return
     */
    List<String> listChannels(String collectorId);
}
