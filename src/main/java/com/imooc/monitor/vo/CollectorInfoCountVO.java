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
public class CollectorInfoCountVO implements Serializable {

    /**
     * 总数
     */
    private int totalCount;
    /**
     * 运行中
     */
    private int runningCount;

    /**
     * 离线
     */
    private int offlineCount;
}
