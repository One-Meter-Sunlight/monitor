package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取在一定时间范围内的参数值列表 接口返回对象
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/22 11:30
 */
@Data
public class AnalystTimeResponse implements Serializable {

    private static final long serialVersionUID = -1866998991098130641L;

    /**
     * 采样数据
     */
    private AnalystTime data;
    /**
     * 返回结果
     */
    private Result result;

}
