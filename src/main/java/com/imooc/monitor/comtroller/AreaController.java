package com.imooc.monitor.comtroller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.monitor.annotation.PassToken;
import com.imooc.monitor.annotation.UserLoginToken;
import com.imooc.monitor.command.AreaCommand;
import com.imooc.monitor.common.BaseResult;
import com.imooc.monitor.jna.VibSPforND;
import com.imooc.monitor.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前端控制器
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@RestController
@RequestMapping("/area")
@Api(tags = "区域记录", description = "区域记录")
public class AreaController {

    @Resource
    private AreaService areaService;

    /**
     * 查询区域列表
     */
    @UserLoginToken
    @RequestMapping(value = "/listArea", method = RequestMethod.POST)
    @ApiOperation(value = "查询区域列表", notes = "查询区域列表")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listArea(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式") AreaCommand command) {
        return BaseResult.success(areaService.listArea(command));
    }

    /**
     * 分页查询区域列表
     */
    @UserLoginToken
    @RequestMapping(value = "/pageArea", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询区域列表", notes = "分页查询区域列表")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult pageArea(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式") AreaCommand command) {
        return BaseResult.success(areaService.pageArea(command));
    }

    /**
     * 分页查询区域采集器信息列表
     *
     * @param command
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/pageAreaCollector", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询区域采集器信息列表", notes = "分页查询区域采集器信息列表")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult pageAreaCollector(@RequestBody @ApiParam(name = "请求对象", value = "传入JSON格式") AreaCommand command) {
        return BaseResult.success(areaService.pageForAreaCollector(command));
    }

}
