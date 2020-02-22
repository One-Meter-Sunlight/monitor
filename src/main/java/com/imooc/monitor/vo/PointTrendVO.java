package com.imooc.monitor.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 区域采集器趋势数据VO
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/1 20:55
 */
@Data
public class PointTrendVO implements Serializable {

    private Date x;
    private BigDecimal y;

}
