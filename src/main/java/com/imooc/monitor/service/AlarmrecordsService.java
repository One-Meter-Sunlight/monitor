package com.imooc.monitor.service;

import com.baomidou.mybatisplus.service.IService;
import com.imooc.monitor.entity.Alarmrecords;
import com.imooc.monitor.vo.HistoryAlarmreCountVO;

import java.util.List;

/**
 * Alarmrecords Service
 *
 * @author kai.chen
 * @date 2019-12-21
 */
public interface AlarmrecordsService extends IService<Alarmrecords> {

    /**
     * 首页历史报警数量统计
     *
     * @return
     */
    HistoryAlarmreCountVO selectMainHistoryAlarmreCount();

    /**
     * 查询报警记录列表
     *
     * @return
     */
    List<Alarmrecords> listAlarmrecords();
}
