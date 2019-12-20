package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 获取在一定时间范围内的参数值列表 接口返回对象
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/22 11:30
 */
@Data
public class HistoryTrackerResponse implements Serializable {

    private static final long serialVersionUID = 72465619652448346L;

    /**
     * 请求参数
     */
    private List<HistoryTracker> data;
    /**
     * 结果集
     */
    private Result result;

}
