package com.imooc.monitor.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.monitor.entity.CollectorAdlink;
import org.apache.ibatis.annotations.Mapper;

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
}