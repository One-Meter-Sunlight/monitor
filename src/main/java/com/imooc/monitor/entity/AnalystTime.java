package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 时域数据
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/10 20:49
 */
@Data
public class AnalystTime implements Serializable {

    private static final long serialVersionUID = -2107550431571396983L;

    /**
     * 采样率
     */
    private Integer sampling_rate;

    /**
     * 通道相关数据
     */
    private List<Channel> channels;

    @Override
    public String toString() {
        return "AnalystTime{" +
                "sampling_rate=" + sampling_rate +
                ", channels=" + channels +
                '}';
    }
}
