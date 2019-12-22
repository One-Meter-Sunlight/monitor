package com.imooc.monitor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.imooc.monitor.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 采集器数据管理 Mapper 接口
 *
 * @author kai.chen
 * @since 2019-06-23
 */
@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    void addBatchByTableNameSuffix(@Param("records") List<Record> records, @Param("tableNameSuffix") String tableNameSuffix);

    List<Record> listRecordsByTableNameSuffix(Map<String, Object> paramMap);

    List<Record> listRecordsByTableNameSuffixForTransfor(Map<String, Object> paramMap);

}