package com.imooc.monitor.comtroller;

import com.imooc.monitor.annotation.UserLoginToken;
import com.imooc.monitor.command.CollectorAdlinkCommand;
import com.imooc.monitor.common.BaseResult;
import com.imooc.monitor.entity.CollectorAdlink;
import com.imooc.monitor.service.CollectorAdlinkService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 采集器信息管理 Controller
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/19 23:29
 */
@RestController
@RequestMapping("/collectorAdlink")
@Api(tags = "采集器", description = "采集器信息")
public class CollectorAdlinkController {

    @Resource
    private CollectorAdlinkService collectorAdlinkService;

    @UserLoginToken
    @RequestMapping(value = "/listByType/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "按类型查询采集器信息", notes = "按类型查询采集器信息")
    @ApiImplicitParam(name = "type", value = "采集器类型", required = true, paramType = "path", dataType = "String")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listByType(@PathVariable(value = "type") String type) {
        return BaseResult.success(collectorAdlinkService.getListByType(type));
    }

    /**
     * 查询采集器列表
     */
    @UserLoginToken
    @RequestMapping(value = "/listByAreaId/{areaId}", method = RequestMethod.GET)
    @ApiOperation(value = "按分区查询采集器列表", notes = "按分区查询采集器列表")
    @ApiImplicitParam(name = "areaId", value = "区域ID", required = true, paramType = "path", dataType = "String")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listByAreaId(@PathVariable(value = "areaId") String areaId) {
        return BaseResult.success(collectorAdlinkService.selectList(areaId));
    }

    /**
     * 查询所有采集器列表
     */
    @UserLoginToken
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询采集器列表", notes = "查询采集器列表")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult list() {
        return BaseResult.success(collectorAdlinkService.selectList());
    }

    /**
     * 修改采集器信息（采样频率，采样点数，采样间隔）
     */
    @UserLoginToken
    @RequestMapping(value = "/updateByInterface", method = RequestMethod.POST)
    @ApiOperation(value = "修改采集器配置信息", notes = "修改采集器配置信息")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult updateByInterface(@RequestBody @Valid @ApiParam(name = "请求对象", value = "传入JSON格式") CollectorAdlinkCommand command) {
        Boolean aBoolean = collectorAdlinkService.updateByInterface(command);
        if (aBoolean) {
            return BaseResult.success(aBoolean);
        }
        return BaseResult.failure("修改采集器配置信息失败");
    }

    /**
     * 查询单个采集器信息（采样频率，采样点数，采样间隔）
     */
    @UserLoginToken
    @RequestMapping(value = "/getByInterface/{collectorNote}", method = RequestMethod.GET)
    @ApiOperation(value = "查询单个采集器信息", notes = "查询单个采集器信息")
    @ApiImplicitParam(name = "collectorNote", value = "采集器ID", required = true, paramType = "path", dataType = "String")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult getByInterface(@PathVariable(value = "collectorNote") String collectorNote) {
        CollectorAdlink bean = collectorAdlinkService.getByInterface(collectorNote);
        if (null != bean) {
            return BaseResult.success(bean);
        }
        return BaseResult.failure("远程调用查询采集器信息失败");
    }

}
