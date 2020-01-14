package com.imooc.monitor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.imooc.monitor.entity.Alarmrecords;
import com.imooc.monitor.entity.Area;
import com.imooc.monitor.vo.AreaAndCollectorsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Area Dao
 *
 * @author kai.chen
 * @date 2019-12-21
 */
@Mapper
public interface AlarmrecordsMapper extends BaseMapper<Alarmrecords> {

    List<Alarmrecords> select();

    List<Alarmrecords> selectByLimit(@Param("limitCount") int limitCount);

    List<String> selectDistinctCollectorIdByCollctorIds(ArrayList<String> newArrayList);
}
