package com.imooc.monitor.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.imooc.monitor.dao.CollectorAdlinkMapper;
import com.imooc.monitor.dao.RecordMapper;
import com.imooc.monitor.entity.CollectorAdlink;
import com.imooc.monitor.entity.Record;
import com.imooc.monitor.service.CollectorAdlinkService;
import com.imooc.monitor.tool.DateUtil;
import com.imooc.monitor.vo.CollectorInfoCountVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
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

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(CollectorAdlinkServiceImpl.class);

    @Autowired
    private CollectorAdlinkMapper collectorAdlinkMapper;
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public List<CollectorAdlink> getListByType(String type) {
        logger.info("请求类型为：[{}]", type);
        return collectorAdlinkMapper.selectByType(type);
    }

    @Override
    public List<CollectorAdlink> selectList(String areaId) {
        return collectorAdlinkMapper.selectList(areaId);
    }

    @Override
    public CollectorInfoCountVO selectMainCollectorInfoCount() {
        // 当前时间
        Date date = new Date();

        // 创建返回对象
        CollectorInfoCountVO vo = new CollectorInfoCountVO();
        // 总数
        List<CollectorAdlink> collectorAdlinks = collectorAdlinkMapper.select();

        // 采集器总数
        int total = CollectionUtils.isEmpty(collectorAdlinks) ? 0 : collectorAdlinks.size();
        vo.setTotalCount(total);

        // 循环采集器，查询所有采集器最大的一条数据的采集时间和当前的时间进行对比，看是否是离线
        int unUsed = 0;
        int used = 0;
        for (CollectorAdlink collector : collectorAdlinks) {
            // 保存间隔
            Integer saveInterval = collector.getSaveInterval();
            String collectorId = collector.getCollectorId();
            // 查询最大的一条采集记录
            Record record = recordMapper.selectMaxCollectDateByCollectorId(collectorId);
            if (null != record) {
                // 采集时间
                Date collectDate = record.getCollectDate();
                if (DateUtil.getDiffHours(collectDate, date) > saveInterval) {
                    unUsed++;
                } else {
                    used++;
                }
            }
        }

        vo.setOfflineCount(unUsed);
        vo.setRunningCount(used);

        return vo;
    }
}
