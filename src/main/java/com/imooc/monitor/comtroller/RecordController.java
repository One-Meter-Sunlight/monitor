package com.imooc.monitor.comtroller;

import com.imooc.monitor.annotation.UserLoginToken;
import com.imooc.monitor.command.AreaCommand;
import com.imooc.monitor.command.RecordCommand;
import com.imooc.monitor.common.BaseResult;
import com.imooc.monitor.service.AreaService;
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
     * 查询转换后的采集器记录列表
     */
    @UserLoginToken
    @RequestMapping(value = "/listRecordsToTransfor", method = RequestMethod.POST)
    @ApiOperation(value = "查询转换后的采集器记录列表", notes = "查询转换后的采集器记录列表")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listRecordsToTransfor(@RequestBody @Valid @ApiParam(name = "请求对象", value = "传入JSON格式") RecordCommand command) {
        return BaseResult.success(recordService.listRecordsToTransfor(command, command.getCollectorId()));
    }

}
