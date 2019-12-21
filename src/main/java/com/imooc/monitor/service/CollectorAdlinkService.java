package com.imooc.monitor.service;

import com.baomidou.mybatisplus.service.IService;
import com.imooc.monitor.entity.CollectorAdlink;

import java.util.List;

/**
 * 采集器管理服务类
 *
 * @author kai.chen
 * @since 2019-06-23
 */
public interface CollectorAdlinkService extends IService<CollectorAdlink> {

    /**
     * 通过类型查询采集器信息
     *
     * @param type
     * @return
     */
    List<CollectorAdlink> getListByType(String type);

    /**
     * 根据类型查询采集器
     *
     * @return
     */
    List<CollectorAdlink> selectList();
}
