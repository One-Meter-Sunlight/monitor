package com.imooc.monitor.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.imooc.monitor.datasource.DynamicDataSourceContextHolder;
import com.imooc.monitor.entity.*;
import com.imooc.monitor.service.CollectorAdlinkService;
import com.imooc.monitor.service.RecordService;
import com.imooc.monitor.tool.Base64Util;
import com.imooc.monitor.tool.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 类型为1的采集器接口
 *
 * @author kai.chen
 * @date 2019-12-20
 */
@Slf4j
@Service
public class Collector_1_Manager {

    /**
     *
     */
    private static final Logger logger = LoggerFactory.getLogger(Collector_1_Manager.class);

    /**
     * 获取在一定时间范围内的参数值列表
     */
    private static final String URL_GET_PARAM = "http://118.31.7.222:5000/IdwsCUvM/api/historyTracker";
    /**
     * 获取时域数据
     */
    private static final String URL_GET_ANALYST_TIME = "http://118.31.7.222:5000/IdwsCUvM/api/analyst/time";

    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private RecordService recordService;
    @Resource
    private CollectorAdlinkService collectorAdlinkService;

    /**
     * 查询和保存type=1的采集器数据
     */
    public void queryAndSaveCollectorData() {

        // 开始时间
        long startTime = System.currentTimeMillis();

        // 获得数据源，并循环执行
        List<String> dataSourceIds = DynamicDataSourceContextHolder.getDataSourceIds();

        if (!CollectionUtils.isEmpty(dataSourceIds)) {
            for (int i = 0; i < dataSourceIds.size(); i++) {
                String dataSource = dataSourceIds.get(i);
                if (!"center".equalsIgnoreCase(dataSource)) {
                    // 切换test数据源
                    DynamicDataSourceContextHolder.setDataSource(dataSource);
                    List<CollectorAdlink> adlinks = collectorAdlinkService.getListByType("1");
                    if (!CollectionUtils.isEmpty(adlinks)) {
                        queryAndSaveTestCollectorData(adlinks, dataSource);
                    }
                }
            }
        }

        logger.info("保存不同数据源采集器类型为1的采集器数据总耗时：[{}]ms", System.currentTimeMillis() - startTime);
    }

    /**
     * 查询和保存type=1的采集器数据
     *
     * @param adlinks 类型为1的所以采集器集合
     */
    private void queryAndSaveTestCollectorData(List<CollectorAdlink> adlinks, String dataSource) {
        for (int i = 0; i < adlinks.size(); i++) {
            CollectorAdlink adlink = adlinks.get(i);
            // 编号
            String code = adlink.getCollectorNote();
            // 通过接口获取在一定时间范围内的参数值列表
            HistoryTrackerResponse response;
            try {
                response = getHistoryTrackerList(code);
            } catch (Exception e) {
                logger.error("获取在一定时间范围内的参数值列表Http接口异常：{}", e);
                return;
            }
            if (null != response && response.getResult().getCode() == 0 && !CollectionUtils.isEmpty(response.getData())) {
                // 通过查询参数查询相关时域数据
                List<HistoryTracker> data = response.getData();

                // 查询并保存时域数据
                if (!CollectionUtils.isEmpty(data)) {
                    getAndSaveAnalystTimeList(data, adlink, dataSource);
                }
            }
        }
    }

    /**
     * 查询并保存时域数据
     *
     * @param data
     */
    private void getAndSaveAnalystTimeList(List<HistoryTracker> data, CollectorAdlink adlink, String dataSource) {
        // 采集器数据
        List<Record> recordList = Lists.newArrayList();

        for (int i = 0; i < data.size(); i++) {
            HistoryTracker tracker = data.get(i);

            // 采集时间
            Date collectDate = tracker.getDaq_time();

            // 循环请求
            JSONObject object = new JSONObject();
            object.put("data_path", tracker.getData_path());
            object.put("cfg_path", tracker.getCfg_path());
            object.put("display_type", 0);

            ResponseEntity entity = restTemplate.postForEntity(URL_GET_ANALYST_TIME, object, AnalystTimeResponse.class);
            AnalystTimeResponse response = (AnalystTimeResponse) entity.getBody();

            if (null != response && response.getResult().getCode() == 0 && null != response.getData()) {
                AnalystTime analystTime = response.getData();
                // 采样率
                Integer samplingRate = analystTime.getSampling_rate();
                // 通道数据
                List<Channel> channels = analystTime.getChannels();
                if (!CollectionUtils.isEmpty(channels)) {
                    for (int j = 0; j < channels.size(); j++) {
                        Channel channel = channels.get(j);
                        // 对数据进行BASE64解码
                        String name = channel.getName();
                        String enDatas = channel.getDatas();
                        String deDatas;
                        try {
                            deDatas = Base64Util.decode(enDatas);
                        } catch (IOException e) {
                            logger.error("解码名称为：{}的数据异常：{}", name, e);
                            continue;
                        }

                        Record record = new Record();
                        record.setCollectorId(adlink.getCollectorId());
                        record.setCollectorNote(adlink.getCollectorNote());
                        record.setChannel(name);
                        record.setCollectDate(collectDate);
                        record.setRate(samplingRate);
                        record.setDataCount(adlink.getDataCount());
                        record.setG(deDatas);

                        recordList.add(record);
                    }
                }
            }
        }

        // 保存相关数据
        if (!CollectionUtils.isEmpty(recordList)) {
            // 切换test数据源
            DynamicDataSourceContextHolder.setDataSource(dataSource);
            recordService.addBatchByTableNameSuffix(recordList, adlink.getCollectorId());
        }
    }

    /**
     * 通过接口获取在一定时间范围内的参数值列表
     *
     * @param code 采集器数据源编号
     * @return HistoryTrackerResponse 返回信息
     */
    private HistoryTrackerResponse getHistoryTrackerList(String code) {
        Date now = new Date();
        Date endTime = now;
        Date startTime = DateUtil.addHours(now, -1);
        String startTimeStr = DateUtil.formatSlash(startTime);
        String endTimeStr = DateUtil.formatSlash(endTime);

        JSONObject object = new JSONObject();
        object.put("device_serialnum", code);
        object.put("param_ids", new int[]{0});
        object.put("start_time", startTimeStr);
        object.put("end_time", endTimeStr);

        logger.info("请求参数时间范围为：" + JSON.toJSONString(object));

        /*object.put("start_time", "2019/11/19 10:05:05");
        object.put("end_time", "2019/12/21 10:05:05");*/

        ResponseEntity entity = restTemplate.postForEntity(URL_GET_PARAM, object, HistoryTrackerResponse.class);
        HistoryTrackerResponse response = (HistoryTrackerResponse) entity.getBody();

        return response;
    }


}
