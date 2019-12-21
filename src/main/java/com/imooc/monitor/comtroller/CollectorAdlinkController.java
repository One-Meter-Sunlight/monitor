package com.imooc.monitor.comtroller;

import com.imooc.monitor.annotation.UserLoginToken;
import com.imooc.monitor.common.BaseResult;
import com.imooc.monitor.entity.CollectorAdlink;
import com.imooc.monitor.service.CollectorAdlinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ApiOperation(value = "查询采集器信息", notes = "通过type-采集器类型查询采集器信息")
    @ApiImplicitParam(name = "type", value = "采集器类型", required = true, paramType = "path", dataType = "String")
    @ApiResponse(response = CollectorAdlink.class, code = 200, message = "接口返回对象参数")
    BaseResult getCollectorAdlinkById(@PathVariable(value = "type") String type) {
        return BaseResult.success(collectorAdlinkService.getListByType(type));
    }

    @UserLoginToken
    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ApiOperation(value = "查询采集器信息列表", notes = "查询采集器信息列表")
    @ApiResponse(response = List.class, code = 200, message = "接口返回对象参数")
    BaseResult pageList() {
        return BaseResult.success(collectorAdlinkService.selectList());
    }

}
