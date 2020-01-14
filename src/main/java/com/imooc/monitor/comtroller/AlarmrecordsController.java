package com.imooc.monitor.comtroller;

import com.imooc.monitor.annotation.UserLoginToken;
import com.imooc.monitor.common.BaseResult;
import com.imooc.monitor.service.AlarmrecordsService;
import com.imooc.monitor.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 前端控制器
 *
 * @author kai.chen
 * @since 2019-06-29
 */
@RestController
@RequestMapping("/alarmrecords")
@Api(tags = "报警记录", description = "报警记录")
public class AlarmrecordsController {

    @Resource
    private AlarmrecordsService alarmrecordsService;

    /**
     * 查询区域列表
     */
    @UserLoginToken
    @RequestMapping(value = "/listAlarmrecords", method = RequestMethod.GET)
    @ApiOperation(value = "查询报警记录列表", notes = "查询报警记录列表")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult listAlarmrecords() {
        return BaseResult.success(alarmrecordsService.listAlarmrecords());
    }

}
