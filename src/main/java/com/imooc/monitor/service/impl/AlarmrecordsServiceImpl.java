package com.imooc.monitor.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.imooc.monitor.dao.AlarmrecordsMapper;
import com.imooc.monitor.entity.Alarmrecords;
import com.imooc.monitor.service.AlarmrecordsService;
import com.imooc.monitor.vo.HistoryAlarmreCountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Alarmrecords Service实现
 *
 * @author kai.chen
 * @date 2019-12-21
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AlarmrecordsServiceImpl extends ServiceImpl<AlarmrecordsMapper, Alarmrecords> implements AlarmrecordsService {

    @Autowired
    private AlarmrecordsMapper alarmrecordsMapper;

    @Override
    public HistoryAlarmreCountVO selectMainHistoryAlarmreCount() {
        // 创建返回对象
        HistoryAlarmreCountVO vo = new HistoryAlarmreCountVO();
        // 告警总数
        List<Alarmrecords> alarmrecordsList = alarmrecordsMapper.select();

        vo.setAlarmreCount(CollectionUtils.isEmpty(alarmrecordsList) ? 0 : alarmrecordsList.size());
        vo.setCalarmreCount(0);
        vo.setDalarmreCount(0);
        vo.setTalarmreCount(0);

        return vo;
    }

    @Override
    public List<Alarmrecords> listAlarmrecords() {
        return alarmrecordsMapper.selectByLimit(5);
    }
}
