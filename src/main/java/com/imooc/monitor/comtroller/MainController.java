package com.imooc.monitor.comtroller;

import com.imooc.monitor.annotation.UserLoginToken;
import com.imooc.monitor.common.BaseResult;
import com.imooc.monitor.service.AlarmrecordsService;
import com.imooc.monitor.service.CollectorAdlinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 主页管理 Controller
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/19 23:29
 */
@RestController
@RequestMapping("/main")
@Api(tags = "主页", description = "主页信息")
public class MainController {

    @Resource
    private AlarmrecordsService alarmrecordsService;
    @Resource
    private CollectorAdlinkService collectorAdlinkService;

    @UserLoginToken
    @RequestMapping(value = "/historyAlarmre", method = RequestMethod.GET)
    @ApiOperation(value = "首页历史报警数量统计", notes = "首页历史报警数量统计")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult historyAlarmre() {
        return BaseResult.success(alarmrecordsService.selectMainHistoryAlarmreCount());
    }

    @UserLoginToken
    @RequestMapping(value = "/collectorInfo", method = RequestMethod.GET)
    @ApiOperation(value = "首页传感器概况", notes = "首页传感器概况")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult collectorInfo() {
        return BaseResult.success(collectorAdlinkService.selectMainCollectorInfoCount());
    }

    @UserLoginToken
    @RequestMapping(value = "/collectorHealthInfo", method = RequestMethod.GET)
    @ApiOperation(value = "首页设备健康状况", notes = "首页设备健康状况")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult collectorHealthInfo() {
        return BaseResult.success(collectorAdlinkService.selectMainCollectorHealthInfoCount());
    }

}
