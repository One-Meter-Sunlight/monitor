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
public class HistoryAlarmreCountVO implements Serializable {

    /**
     * 报警总数
     */
    private int alarmreCount;
    /**
     * 温度报警数量
     */
    private int talarmreCount;

    /**
     * 报警设备C区
     */
    private int calarmreCount;

    /**
     * 报警设备D区
     */
    private int dalarmreCount;
}
