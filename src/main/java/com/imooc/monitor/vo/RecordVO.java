package com.imooc.monitor.vo;

import com.imooc.monitor.entity.Record;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 区域采集器列表统计
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/7/1 20:55
 */
@Data
public class RecordVO extends Record implements Serializable {

    /**
     * 采集器数据集合
     */
    private List<PointVO> points;

}
