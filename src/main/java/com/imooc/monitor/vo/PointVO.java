package com.imooc.monitor.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 区域采集器列表统计
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/1 20:55
 */
@Data
public class PointVO implements Serializable {

    private int x;
    private BigDecimal y;

}
