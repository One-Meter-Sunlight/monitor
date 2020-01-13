package com.imooc.monitor.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.imooc.monitor.dao.AlarmrecordsMapper;
import com.imooc.monitor.entity.Alarmrecords;
import com.imooc.monitor.service.AlarmrecordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
