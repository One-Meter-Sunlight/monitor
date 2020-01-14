package com.imooc.monitor.service;

import com.baomidou.mybatisplus.service.IService;
import com.imooc.monitor.entity.CollectorAdlink;
import com.imooc.monitor.vo.CollectorHealthInfoCountVO;
import com.imooc.monitor.vo.CollectorInfoCountVO;

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
    List<CollectorAdlink> selectList(String areaId);

    /**
     * 首页传感器概况
     *
     * @return
     */
    CollectorInfoCountVO selectMainCollectorInfoCount();

    /**
     * 首页设备健康状况
     *
     * @return
     */
    CollectorHealthInfoCountVO selectMainCollectorHealthInfoCount();
}
