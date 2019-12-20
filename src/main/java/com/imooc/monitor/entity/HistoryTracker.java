package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 获取在一定时间范围内的参数值列表 接口返回对象
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/22 11:30
 */
@Data
public class HistoryTracker implements Serializable {

    private static final long serialVersionUID = 7228123239652448346L;

    /**
     * 配置文件地址
     */
    private String cfg_path;
    /**
     * 采集时间
     */
    private Date daq_time;
    /**
     * 数据文件地址
     */
    private String data_path;
    /**
     * 是否有原始数据
     */
    private Object is_data;

    /**
     * 请求参数对应的值
     */
    private List<BigDecimal> param_value;

}
