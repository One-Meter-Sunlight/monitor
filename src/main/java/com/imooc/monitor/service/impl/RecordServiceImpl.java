package com.imooc.monitor.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.imooc.monitor.command.RecordCommand;
import com.imooc.monitor.dao.RecordMapper;
import com.imooc.monitor.entity.Record;
import com.imooc.monitor.jna.VibSPforND;
import com.imooc.monitor.service.RecordService;
import com.imooc.monitor.vo.PointVO;
import com.imooc.monitor.vo.RecordVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 采集器数据服务实现类
 *
 * @author kai.chen
 * @since 2019-06-23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void addBatchByTableNameSuffix(List<Record> records, String tableNameSuffix) {
        recordMapper.addBatchByTableNameSuffix(records, tableNameSuffix);
    }

    /**
     * 查询采集器通道数据记录
     *
     * @param collectorId
     * @return
     */
    @Override
    public List<String> listChannels(String collectorId) {
        return recordMapper.queryChannelsByCollectorId(collectorId);
    }

    /**
     * 根据条件查询采集器数据记录
     *
     * @param command
     * @return
     */
    @Override
    public List<Record> listRecords(RecordCommand command, String collectorId) {
        Map<String, Object> paramMap = Maps.newHashMap();
        if (StringUtils.isNoneBlank(collectorId)) {
            paramMap.put("collectorId", collectorId);
        }
        if (StringUtils.isNoneBlank(command.getChannel())) {
            paramMap.put("channel", command.getChannel());
        }
        if (null != command.getCollectBeginDate()) {
            paramMap.put("collectBeginDate", command.getCollectBeginDate());
        }
        if (null != command.getCollectEndDate()) {
            paramMap.put("collectEndDate", command.getCollectEndDate());
        }
        return recordMapper.listRecordsByTableNameSuffix(paramMap);
    }

    /**
     * 查询转换后的采集器记录列表
     *
     * @param command
     * @param collectorId
     * @return
     */
    @Override
    public List<RecordVO> listRecordsToTransfor(RecordCommand command, String collectorId) {
        Map<String, Object> paramMap = Maps.newHashMap();
        if (StringUtils.isNoneBlank(collectorId)) {
            paramMap.put("collectorId", collectorId);
        }
        if (StringUtils.isNoneBlank(command.getChannel())) {
            paramMap.put("channel", command.getChannel());
        }
        if (null != command.getCollectDate()) {
            paramMap.put("collectDate", command.getCollectDate());
        }
        List<Record> records = recordMapper.listRecordsByTableNameSuffixForTransfor(paramMap);
        if (CollectionUtils.isEmpty(records)) {
            return Lists.newArrayList();
        }

        // 转换
        List<RecordVO> recordVOList = Lists.newArrayList();
        for (int i = 0; i < records.size(); i++) {
            Record record = records.get(i);
            RecordVO vo = new RecordVO();
            BeanUtils.copyProperties(record, vo);

            String g = record.getG();
            if (StringUtils.isNoneBlank(g)) {
                List<String> list = Splitter.on("|").trimResults().omitEmptyStrings().splitToList(g);
                List<PointVO> pointVOS = Lists.newArrayList();
                for (int j = 0; j < list.size(); j++) {
                    PointVO pointVO = new PointVO();
                    pointVO.setX(j);
                    pointVO.setY(new BigDecimal(list.get(j)));
                    pointVOS.add(pointVO);
                }

                vo.setPoints(pointVOS);
            }

            recordVOList.add(vo);
        }

        return recordVOList;
    }

    /**
     * 查询转换后的采集器包络图数据
     *
     * @param command
     * @param collectorId
     * @return
     */
    @Override
    public List<RecordVO> listEnvelopeRecordsToTransfor(RecordCommand command, String collectorId) {
        Map<String, Object> paramMap = Maps.newHashMap();
        if (StringUtils.isNoneBlank(collectorId)) {
            paramMap.put("collectorId", collectorId);
        }
        if (StringUtils.isNoneBlank(command.getChannel())) {
            paramMap.put("channel", command.getChannel());
        }
        if (null != command.getCollectDate()) {
            paramMap.put("collectDate", command.getCollectDate());
        }
        List<Record> records = recordMapper.listRecordsByTableNameSuffixForTransfor(paramMap);
        if (CollectionUtils.isEmpty(records)) {
            return Lists.newArrayList();
        }

        // 转换
        List<RecordVO> recordVOList = Lists.newArrayList();
        for (int i = 0; i < records.size(); i++) {
            Record record = records.get(i);
            RecordVO vo = new RecordVO();
            BeanUtils.copyProperties(record, vo);

            String g = record.getG();
            if (StringUtils.isNoneBlank(g)) {
                List<String> list = Splitter.on("|").trimResults().omitEmptyStrings().splitToList(g);
                double[] param = new double[list.size()];
                double[] m_timeData = new double[list.size()];
                for (int k = 0; k < list.size(); k++) {
                    m_timeData[k] = Double.valueOf(list.get(k));
                }
                // 包络图转换
                VibSPforND.vi.GetEnvelope(param, m_timeData, list.size());
                if (param.length > 0) {
                    List<PointVO> pointVOS = Lists.newArrayList();
                    for (int j = 0; j < param.length; j++) {
                        PointVO pointVO = new PointVO();
                        pointVO.setX(j);
                        pointVO.setY(new BigDecimal(param[j]));
                        pointVOS.add(pointVO);
                    }
                    vo.setPoints(pointVOS);
                }
            }

            recordVOList.add(vo);
        }

        return recordVOList;
    }
}
