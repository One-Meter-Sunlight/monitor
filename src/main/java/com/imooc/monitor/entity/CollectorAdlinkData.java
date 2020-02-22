package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 采集器信息对象
 */
@Data
public class CollectorAdlinkData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 57231124353133666L;

    private Integer parse_freq;  // 采样频率

    private Integer sample_interval;  // 采样间隔

    private Integer spectrum_lines; // 采样点数

}