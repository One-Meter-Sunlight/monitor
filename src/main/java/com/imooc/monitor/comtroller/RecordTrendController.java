package com.imooc.monitor.comtroller;

import com.imooc.monitor.annotation.UserLoginToken;
import com.imooc.monitor.command.RecordCommand;
import com.imooc.monitor.common.BaseResult;
import com.imooc.monitor.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 前端控制器
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@RestController
@RequestMapping("/recordTrend")
@Api(tags = "采集器数据趋势记录", description = "采集器数据趋势记录")
public class RecordTrendController {

    @Resource
    private RecordService recordService;

    /**
     * 查询速度趋势数据
     */
    @UserLoginToken
    @RequestMapping(value = "/listVelocityTrend", method = RequestMethod.POST)
    @ApiOperation(value = "查询速度趋势数据", notes = "查询速度趋势数据")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listVelocityTrend(@RequestBody @Valid @ApiParam(name = "请求对象", value = "传入JSON格式") RecordCommand command) {
        return BaseResult.success(recordService.listRecordsByMmsRms(command, command.getCollectorId()));
    }

    /**
     * 查询位移趋势数据
     */
    @UserLoginToken
    @RequestMapping(value = "/listDisplacementTrend", method = RequestMethod.POST)
    @ApiOperation(value = "查询位移趋势数据", notes = "查询位移趋势数据")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listDisplacementTrend(@RequestBody @Valid @ApiParam(name = "请求对象", value = "传入JSON格式") RecordCommand command) {
        return BaseResult.success(recordService.listRecordsByUmPp(command, command.getCollectorId()));
    }

    /**
     * 查询位加速度趋势数据
     */
    @UserLoginToken
    @RequestMapping(value = "/listAccelerationTrend", method = RequestMethod.POST)
    @ApiOperation(value = "查询位加速度趋势数据", notes = "查询位加速度趋势数据")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listAccelerationTrend(@RequestBody @Valid @ApiParam(name = "请求对象", value = "传入JSON格式") RecordCommand command) {
        return BaseResult.success(recordService.listRecordsByGrms(command, command.getCollectorId()));
    }

}
