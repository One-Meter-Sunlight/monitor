package com.imooc.monitor.vo;

import com.imooc.monitor.entity.Area;
import com.imooc.monitor.entity.CollectorAdlink;
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
public class AreaAndCollectorsVO extends Area implements Serializable {

    /**
     * 采集器集合
     */
    private List<CollectorAdlink> collectors;

}
