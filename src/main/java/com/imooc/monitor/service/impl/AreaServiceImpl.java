package com.imooc.monitor.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.imooc.monitor.command.AreaCommand;
import com.imooc.monitor.dao.AreaMapper;
import com.imooc.monitor.entity.Area;
import com.imooc.monitor.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Area Service实现
 *
 * @author kai.chen
 * @date 2019-12-21
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public Page<Area> pageArea(AreaCommand command) {
        // 分页查询
        Page<Area> page = new Page<>(command.getCurrent(), command.getSize());

        Map<String, Object> condition = new HashMap<>();
        if (StringUtils.isNotBlank(command.getAreaName())) {
            condition.put("areaName", command.getAreaName());
        }
        if (StringUtils.isNotBlank(command.getAreaId())) {
            condition.put("areaId", command.getAreaId());
        }

        List<Area> areas = areaMapper.selectForPage(page, condition);
        page.setRecords(areas);

        return page;
    }
}
