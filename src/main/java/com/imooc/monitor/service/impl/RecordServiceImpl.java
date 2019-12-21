package com.imooc.monitor.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.imooc.monitor.dao.RecordMapper;
import com.imooc.monitor.entity.Record;
import com.imooc.monitor.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
