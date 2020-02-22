package com.imooc.monitor.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.imooc.monitor.command.CollectorAdlinkCommand;
import com.imooc.monitor.dao.AlarmrecordsMapper;
import com.imooc.monitor.dao.CollectorAdlinkMapper;
import com.imooc.monitor.dao.RecordMapper;
import com.imooc.monitor.entity.CollectorAdlink;
import com.imooc.monitor.entity.CollectorAdlinkResponse;
import com.imooc.monitor.entity.Record;
import com.imooc.monitor.service.CollectorAdlinkService;
import com.imooc.monitor.tool.DateUtil;
import com.imooc.monitor.vo.CollectorHealthInfoCountVO;
import com.imooc.monitor.vo.CollectorInfoCountVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * 获取在一定时间范围内的参数值列表
     */
    private static final String URL_PARAM = "http://118.31.7.222:5000/IdwsCUvM/api/daq_devices";

    @Autowired
    private AlarmrecordsMapper alarmrecordsMapper;
    @Autowired
    private CollectorAdlinkMapper collectorAdlinkMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CollectorAdlink getByInterface(String collectorNote) {
        CollectorAdlink adlink = null;
        try {
            ResponseEntity entity = restTemplate.getForEntity(URL_PARAM + "/" + collectorNote, CollectorAdlinkResponse.class);
            CollectorAdlinkResponse response = (CollectorAdlinkResponse) entity.getBody();
            if (null != response.getResult() && response.getResult().getCode() == 0) {
                adlink = new CollectorAdlink();
                adlink.setRate(response.getData().getParse_freq());
                adlink.setDataCount(response.getData().getSpectrum_lines());
                adlink.setSaveInterval(response.getData().getSample_interval());
                return adlink;
            }
        } catch (Exception e) {
            log.error("远程调用查询采集器信息失败", e);
        }

        return adlink;
    }

    @Override
    public Boolean updateByInterface(CollectorAdlinkCommand command) {
        collectorAdlinkMapper.updateByInterface(command.getCollectorId(), command.getRate(), command.getDataCount(), command.getSaveInterval());
        // 通过接口修改远程传感器配置信息
        JSONObject object = new JSONObject();
        object.put("parse_freq", command.getRate());
        object.put("spectrum_lines", command.getDataCount());
        object.put("sampling_interval", command.getSaveInterval());

        logger.info("请求参数为：" + JSON.toJSONString(object));

        try {
            restTemplate.put(URL_PARAM + "/" + command.getCollectorNote(), object);
        } catch (Exception e) {
            log.error("订单取消失败", e);
            return false;
        }
        return true;
    }

    @Override
    public List<CollectorAdlink> selectList() {
        return collectorAdlinkMapper.select();
    }

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
                if (DateUtil.getDiffSeconds(collectDate, date) > saveInterval) {
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

    /**
     * 首页设备健康状况
     *
     * @return
     */
    @Override
    public CollectorHealthInfoCountVO selectMainCollectorHealthInfoCount() {

        // 创建返回对象
        CollectorHealthInfoCountVO vo = new CollectorHealthInfoCountVO();
        // 总数
        List<CollectorAdlink> collectorAdlinks = collectorAdlinkMapper.select();

        if (CollectionUtils.isEmpty(collectorAdlinks)) {
            vo.setDangerCount(0);
            vo.setAlarmreCount(0);
            vo.setNormalCount(0);
            return vo;
        }

        // 循环采集器，并查询是否有告警记录
        Set<String> collectors = collectorAdlinks.stream().map(CollectorAdlink::getCollectorId).collect(Collectors.toSet());
        // 查询所有采集器告警记录
        List<String> resultCollectorIds = alarmrecordsMapper.selectDistinctCollectorIdByCollctorIds(Lists.newArrayList(collectors));
        if (!CollectionUtils.isEmpty(resultCollectorIds)) {
            if (collectors.size() == resultCollectorIds.size()) {
                vo.setNormalCount(0);
                vo.setAlarmreCount(collectors.size());
            } else {
                vo.setAlarmreCount(resultCollectorIds.size());
                vo.setNormalCount(collectors.size() - resultCollectorIds.size());
            }
        } else {
            vo.setNormalCount(collectors.size());
            vo.setAlarmreCount(0);
        }

        vo.setDangerCount(0);

        return vo;
    }
}
