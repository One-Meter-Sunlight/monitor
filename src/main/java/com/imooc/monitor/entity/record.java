package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 采集器通道数据保存
 */
@Data
public class record implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 54039687874314072L;

    private Integer idd;

    private String collectorId;

    private String channel;

    private Date collectDate;

    private String collectorNote;

    private Integer rotationRate;

    private Float temprature;

    private Integer rate;

    private Integer dataCount;

    private String g;

    private String fft;

    private Float gRms;

    private Float gPeak;

    private Float mmsRms;

    private Float mmsPeak;

    private Float umPp;

}