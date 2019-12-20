package com.imooc.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.monitor.annotation.DataSource;
import com.imooc.monitor.dao.CollectorAdlinkMapper;
import com.imooc.monitor.entity.CollectorAdlink;
import com.imooc.monitor.service.CollectorAdlinkService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采集器管理服务实现类
 *
 * @author kai.chen
 * @since 2019-06-23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CollectorAdlinkServiceImpl extends ServiceImpl<CollectorAdlinkMapper, CollectorAdlink>
        implements CollectorAdlinkService {

    private static final Logger logger = LoggerFactory.getLogger(CollectorAdlinkServiceImpl.class);

    @Autowired
    private CollectorAdlinkMapper collectorAdlinkMapper;

    @Override
    public List<CollectorAdlink> getListByType(String type) {
        logger.info("请求类型为：[{}]", type);
        return collectorAdlinkMapper.selectByType(type);
    }
}
