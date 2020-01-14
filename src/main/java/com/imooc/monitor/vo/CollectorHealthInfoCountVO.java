package com.imooc.monitor.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 首页历史报警数量统计
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/1 20:55
 */
@Data
public class CollectorHealthInfoCountVO implements Serializable {

    /**
     * 正常
     */
    private int normalCount;
    /**
     * 告警
     */
    private int alarmreCount;

    /**
     * 危险
     */
    private int dangerCount;
}
