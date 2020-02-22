package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 采集器信息对象
 */
@Data
public class CollectorAdlinkResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请求参数
     */
    private CollectorAdlinkData data;
    /**
     * 结果集
     */
    private Result result;

}