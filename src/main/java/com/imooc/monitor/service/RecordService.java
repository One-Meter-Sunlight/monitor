package com.imooc.monitor.service;

import com.baomidou.mybatisplus.service.IService;
import com.imooc.monitor.command.RecordCommand;
import com.imooc.monitor.entity.Alarmrecords;
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
     * 查询采集器通道数据记录
     *
     * @param collectorId
     * @return
     */
    List<String> listChannels(String collectorId);

    /**
     * 查询转换后的采集器时域图数据
     *
     * @param command
     * @param collectorId
     * @return
     */
    List<RecordVO> listRecordsToTransform(RecordCommand command, String collectorId);

    /**
     * 查询转换后的采集器包络图数据
     *
     * @param command
     * @param collectorId
     * @return
     */
    List<RecordVO> listEnvelopeRecordsToTransform(RecordCommand command, String collectorId);

    /**
     * 查询转换后的采集器包络频谱图数据
     *
     * @param command
     * @param collectorId
     * @return
     */
    List<RecordVO> listEnvelopeSpectrumRecordsToTransform(RecordCommand command, String collectorId);

    /**
     * 查询由加速度时域计算速度频谱
     *
     * @param command
     * @param collectorId
     * @return
     */
    List<RecordVO> listA2VRecordsToTransform(RecordCommand command, String collectorId);

    /**
     * 查询加速度时域数组 -> 频谱图
     *
     * @param command
     * @param collectorId
     * @return
     */
    List<RecordVO> listA2ARecordsToTransform(RecordCommand command, String collectorId);

    void addBatchAlarmrecordsList(List<Alarmrecords> alarmrecordsList);
}
