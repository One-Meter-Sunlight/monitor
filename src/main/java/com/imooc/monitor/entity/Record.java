package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 采集器通道数据保存
 */
@Data
public class Record implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 54039687874314072L;

    /**
     * 主键
     */
    private Integer idd;
    /**
     * 采集器ID
     */
    private String collectorId;
    /**
     * 通道编号
     */
    private String channel;
    /**
     * 采集时间
     */
    private Date collectDate;
    /**
     * 采集器备注
     */
    private String collectorNote;
    /**
     * 转速
     */
    private Integer rotationRate;
    /**
     * 温度
     */
    private Float temprature;
    /**
     * 采样频率
     */
    private Integer rate;
    /**
     * 采样点数
     */
    private Integer dataCount;
    /**
     * 原始数据
     */
    private String g;

    private String fft;

    private Float gRms;

    private Float gPeak;

    private Float mmsRms;

    private Float mmsPeak;

    private Float umPp;

}