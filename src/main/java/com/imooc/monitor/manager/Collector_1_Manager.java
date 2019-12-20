package com.imooc.monitor.manager;

import com.alibaba.fastjson.JSONObject;
import com.imooc.monitor.datasource.DynamicDataSourceContextHolder;
import com.imooc.monitor.entity.AnalystTime;
import com.imooc.monitor.entity.AnalystTimeResponse;
import com.imooc.monitor.entity.Channel;
import com.imooc.monitor.entity.CollectorAdlink;
import com.imooc.monitor.entity.HistoryTracker;
import com.imooc.monitor.entity.HistoryTrackerResponse;
import com.imooc.monitor.service.CollectorAdlinkService;
import com.imooc.monitor.tool.Base64Util;
import com.imooc.monitor.tool.DateUtil;
import lombok.extern.slf4j.Slf4j;
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
    private CollectorAdlinkService collectorAdlinkService;

    /**
     * 查询和保存type=1的采集器数据
     */
    public void queryAndSaveCollectorData() {

        // 获得数据源，并循环执行
        List<String> dataSourceIds = DynamicDataSourceContextHolder.getDataSourceIds();

        if (!CollectionUtils.isEmpty(dataSourceIds)) {
            for (int i = 0; i < dataSourceIds.size(); i++) {
                String dataSource = dataSourceIds.get(i);
                if ("test".equalsIgnoreCase(dataSource)) {
                    // 切换test数据源
                    DynamicDataSourceContextHolder.setDataSource("test");
                    List<CollectorAdlink> adlinks = collectorAdlinkService.getListByType("1");
                    if (!CollectionUtils.isEmpty(adlinks)) {
                        queryAndSaveTestCollectorData(adlinks);
                    }
                }
            }
        }
    }

    /**
     * 查询和保存type=1的采集器数据
     *
     * @param adlinks 类型为1的所以采集器集合
     */
    private void queryAndSaveTestCollectorData(List<CollectorAdlink> adlinks) {
        for (int i = 0; i < adlinks.size(); i++) {
            CollectorAdlink adlink = adlinks.get(i);
            // 编号
            String code = adlink.getCollectorNote();
            // 通过接口获取在一定时间范围内的参数值列表
            HistoryTrackerResponse response;
            try {
                response = getHistoryTrackerList(code);
            } catch (Exception e) {
                log.error("获取在一定时间范围内的参数值列表Http接口异常：{}", e);
                return;
            }
            if (null != response && response.getResult().getCode() == 0 && !CollectionUtils.isEmpty(response.getData())) {
                // 通过查询参数查询相关时域数据
                List<HistoryTracker> data = response.getData();

                // 查询并保存时域数据
                getAndSaveAnalystTimeList(data);
            }
        }
    }

    /**
     * 查询并保存时域数据
     *
     * @param data
     */
    private void getAndSaveAnalystTimeList(List<HistoryTracker> data) {
        if (!CollectionUtils.isEmpty(data)) {
            for (int i = 0; i < data.size(); i++) {
                HistoryTracker tracker = data.get(i);

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
                    Integer sampling_rate = analystTime.getSampling_rate();
                    // 通道数据
                    List<Channel> channels = analystTime.getChannels();
                    if (!CollectionUtils.isEmpty(channels)) {
                        for (int j = 0; j < channels.size(); j++) {
                            Channel channel = channels.get(j);
                            // 对数据进行BASE64解码
                            String name = channel.getName();
                            String enDatas = channel.getDatas();
                            String deDatas = null;
                            try {
                                deDatas = Base64Util.decode(enDatas);
                            } catch (IOException e) {
                                log.error("解码名称为：{}的数据异常：{}", name, e);
                            }


                            System.out.println(deDatas);
                        }
                    }
                }
            }
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
        /*object.put("start_time", startTimeStr);
        object.put("end_time", endTimeStr);*/

        object.put("start_time", "2019/11/19 10:05:05");
        object.put("end_time", "2019/12/21 10:05:05");

        ResponseEntity entity = restTemplate.postForEntity(URL_GET_PARAM, object, HistoryTrackerResponse.class);
        HistoryTrackerResponse response = (HistoryTrackerResponse) entity.getBody();

        return response;
    }


}
