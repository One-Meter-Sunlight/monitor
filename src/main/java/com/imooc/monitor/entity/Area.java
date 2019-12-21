package com.imooc.monitor.entity;

import java.io.Serializable;

/**
 * Area 实体类
 *
 * @author kai.chen
 * @date 2019-12-21
 */
public class Area implements Serializable {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * areaId
     */
    private String areaId;
    /**
     * areaName
     */
    private String areaName;

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return this.areaName;
    }
}
