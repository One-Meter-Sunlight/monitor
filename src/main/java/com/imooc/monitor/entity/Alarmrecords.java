package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2020/1/13 22:08
 */
@Data
public class Alarmrecords implements Serializable {

    private Date happenTime;

    private String areaId;

    private String collectorId;

    private String collectorNote;

    private String channelIndex;

    private String channelNote;

    private String content;

    private String remark;
}
