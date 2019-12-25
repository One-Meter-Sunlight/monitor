package com.imooc.monitor.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * 采集器数据请求参数
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/29 14:14
 */
public class RecordCommand implements Serializable {

    /**
     * 采集器
     */
    @NotEmpty(message = "collectorId is null")
    private String collectorId;
    /**
     * 通道
     */
    @NotEmpty(message = "channel is null")
    private String channel;
    /**
     * 采集时间
     */
    private Date collectDate;

    /**
     * 采集开始时间
     */
    private Date collectBeginDate;

    /**
     * 采集结束时间
     */
    private Date collectEndDate;

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public Date getCollectBeginDate() {
        return collectBeginDate;
    }

    public void setCollectBeginDate(Date collectBeginDate) {
        this.collectBeginDate = collectBeginDate;
    }

    public Date getCollectEndDate() {
        return collectEndDate;
    }

    public void setCollectEndDate(Date collectEndDate) {
        this.collectEndDate = collectEndDate;
    }
}
