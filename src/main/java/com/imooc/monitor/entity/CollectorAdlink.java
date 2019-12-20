package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 采集器信息对象
 */
@Data
public class CollectorAdlink implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5723161825353133666L;

    private String collectorId;

    private Integer areaId;

    private String collectorNote;

    private String collectorIp;

    private String channel0Note;

    private String channel1Note;

    private String channel2Note;

    private String channel3Note;

    private String loginName;

    private String loginPwd;

    private Integer rate;

    private Integer dataCount;

    private Integer saveInterval;

    private Integer sensor0Sensitivity;

    private Integer sensor1Sensitivity;

    private Integer sensor2Sensitivity;

    private Integer sensor3Sensitivity;

    private Float channel0Threshold;

    private Float channel1Threshold;

    private Float channel2Threshold;

    private Float channel3Threshold;

    private Integer tachometer;

    private Integer machinetype;

    private String machinepara;

    /**
     * 类型，每种采集器都有一种类型，如 c_0001 = 1
     */
    private String type;

}