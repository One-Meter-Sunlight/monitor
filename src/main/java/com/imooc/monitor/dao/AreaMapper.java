package com.imooc.monitor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.imooc.monitor.entity.Area;
import com.imooc.monitor.vo.AreaAndCollectorsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Area Dao
 *
 * @author kai.chen
 * @date 2019-12-21
 */
@Mapper
public interface AreaMapper extends BaseMapper<Area> {

    /**
     * 分页查询
     *
     * @param page
     * @param condition
     * @return
     */
    List<Area> selectForPage(Page<Area> page, Map<String, Object> condition);

    /**
     * 列表查询
     *
     * @param condition
     * @return
     */
    List<Area> selectForList(Map<String, Object> condition);

    /**
     * 分页查询区域采集器
     *
     * @return
     */
    List<AreaAndCollectorsVO> selectAreaAndCollectorsForPage(Page<AreaAndCollectorsVO> page, Map<String, Object> condition);
}
