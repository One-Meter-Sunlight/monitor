package com.imooc.monitor.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 采集器数据请求参数
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/29 14:14
 */
@Data
public class RecordCommand {

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
}
