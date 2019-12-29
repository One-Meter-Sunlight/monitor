package com.imooc.monitor.comtroller;

import com.imooc.monitor.annotation.UserLoginToken;
import com.imooc.monitor.command.RecordCommand;
import com.imooc.monitor.common.BaseResult;
import com.imooc.monitor.service.RecordService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 前端控制器
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@RestController
@RequestMapping("/record")
@Api(tags = "采集器数据记录", description = "采集器数据记录")
public class RecordController {

    @Resource
    private RecordService recordService;

    /**
     * 查询采集器数据记录
     */
    @UserLoginToken
    @RequestMapping(value = "/listRecords", method = RequestMethod.POST)
    @ApiOperation(value = "查询采集器数据记录", notes = "查询采集器数据记录")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listRecords(@RequestBody @Valid @ApiParam(name = "请求对象", value = "传入JSON格式") RecordCommand command) {
        return BaseResult.success(recordService.listRecords(command, command.getCollectorId()));
    }

    /**
     * 查询采集器通道数据记录
     */
    @UserLoginToken
    @RequestMapping(value = "/listChannels/{collectorId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询采集器通道数据记录", notes = "查询采集器通道数据记录")
    @ApiImplicitParam(name = "collectorId", value = "采集器ID", required = true, paramType = "path", dataType = "String")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listByType(@PathVariable(value = "collectorId") String collectorId) {
        return BaseResult.success(recordService.listChannels(collectorId));
    }

    /**
     * 查询转换后的采集器时域图数据
     */
    @UserLoginToken
    @RequestMapping(value = "/listRecordsToTransfor", method = RequestMethod.POST)
    @ApiOperation(value = "查询转换后的采集器时域图数据", notes = "查询转换后的采集器时域图数据")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listRecordsToTransfor(@RequestBody @Valid @ApiParam(name = "请求对象", value = "传入JSON格式") RecordCommand command) {
        return BaseResult.success(recordService.listRecordsToTransfor(command, command.getCollectorId()));
    }

    /**
     * 查询转换后的采集器包络图数据
     */
    @UserLoginToken
    @RequestMapping(value = "/listEnvelopeRecordsToTransfor", method = RequestMethod.POST)
    @ApiOperation(value = "查询转换后的采集器包络图数据", notes = "查询转换后的采集器包络图数据")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listEnvelopeRecordsToTransfor(@RequestBody @Valid @ApiParam(name = "请求对象", value = "传入JSON格式") RecordCommand command) {
        return BaseResult.success(recordService.listEnvelopeRecordsToTransfor(command, command.getCollectorId()));
    }

}
