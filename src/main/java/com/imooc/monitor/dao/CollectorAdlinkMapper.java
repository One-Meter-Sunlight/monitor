package com.imooc.monitor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.imooc.monitor.entity.CollectorAdlink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采集器管理 Mapper 接口
 *
 * @author kai.chen
 * @since 2019-06-23
 */
@Mapper
public interface CollectorAdlinkMapper extends BaseMapper<CollectorAdlink> {

    /**
     * 根据类型查询采集器
     *
     * @param type
     * @return
     */
    List<CollectorAdlink> selectByType(String type);

    /**
     * 根据区域ID查询采集器
     *
     * @return
     */
    List<CollectorAdlink> selectList(String areaId);

    /**
     * 查询所有采集器信息列表
     *
     * @return
     */
    List<CollectorAdlink> select();

    /**
     * 修改采集器配置信息
     *
     * @param collectorId  传感器ID
     * @param rate         采样频率
     * @param dataCount    采样点数
     * @param saveInterval 保存间隔
     */
    void updateByInterface(@Param("collectorId") String collectorId, @Param("rate") Integer rate,
                         @Param("dataCount") Integer dataCount, @Param("saveInterval") Integer saveInterval);
}