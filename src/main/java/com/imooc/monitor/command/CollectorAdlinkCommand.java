package com.imooc.monitor.command;

import lombok.Data;

import java.io.Serializable;

/**
 * 采集器信息请求对象
 */
@Data
public class CollectorAdlinkCommand implements Serializable {

    private String collectorId;

    private String collectorNote;

    private Integer rate;

    private Integer dataCount;

    private Integer saveInterval;

}