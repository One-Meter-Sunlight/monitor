package com.imooc.monitor.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.imooc.monitor.command.AreaCommand;
import com.imooc.monitor.entity.Area;

/**
 * Area Service
 *
 * @author kai.chen
 * @date 2019-12-21
 */
public interface AreaService extends IService<Area> {

    /**
     * 分页查询
     *
     * @param command
     * @return
     */
    Page<Area> pageArea(AreaCommand command);
}
