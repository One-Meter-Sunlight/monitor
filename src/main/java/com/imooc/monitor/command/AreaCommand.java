package com.imooc.monitor.command;

import com.imooc.monitor.common.PageInfo;
import lombok.Data;

/**
 * 区域请求参数
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/29 14:14
 */
@Data
public class AreaCommand extends PageInfo {

    /**
     * areaId
     */
    private String areaId;
    /**
     * areaName
     */
    private String areaName;

}
