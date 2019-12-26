package com.imooc.monitor.comtroller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.monitor.annotation.PassToken;
import com.imooc.monitor.annotation.UserLoginToken;
import com.imooc.monitor.common.BaseResult;
import com.imooc.monitor.entity.User;
import com.imooc.monitor.service.UserService;
import com.imooc.monitor.tool.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理 Controller
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/19 23:29
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户", description = "用户信息")
public class UserController {

    @Resource
    private UserService userService;

    @PassToken
    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "用户登录")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    public BaseResult login(@RequestBody User user) {

        if (null == user || StringUtils.isBlank(user.getUserId())) {
            return BaseResult.failure(403, "请输入登录账号");
        }
        User userForBase = userService.getByUserId(user.getUserId());
        if (userForBase == null) {
            return BaseResult.failure(403, "登录失败,用户不存在");
        } else {
            if (!userForBase.getPassword().equals(user.getPassword())) {
                return BaseResult.failure(403, "登录失败,密码错误");
            } else {
                String token = TokenUtil.getToken(userForBase);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", token);
                userForBase.setPassword(null);
                jsonObject.put("user", userForBase);

                return BaseResult.success(jsonObject);
            }
        }
    }

    @UserLoginToken
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "查询账户信息", notes = "通过AccountId查询用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "path", dataType = "String")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult getUserByAccountId(@PathVariable(value = "userId") String userId) {
        return BaseResult.success(userService.getByUserId(userId));
    }

    @UserLoginToken
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息")
    @ApiResponse(response = Boolean.class, code = 200, message = "接口返回对象参数")
    BaseResult add(@RequestBody User user) {
        return BaseResult.success(userService.addUser(user));
    }

    @UserLoginToken
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult update(@RequestBody User user) {
        return BaseResult.success(userService.updateUser(user));
    }

    @UserLoginToken
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户信息列表", notes = "查询用户信息列表")
    @ApiResponse(response = BaseResult.class, code = 200, message = "接口返回对象参数")
    BaseResult list() {
        return BaseResult.success(userService.getUserList());
    }

}
