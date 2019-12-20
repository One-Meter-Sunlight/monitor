package com.imooc.monitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.monitor.entity.User;

import java.util.List;

/**
 * 用户管理服务类
 *
 * @author kai.chen
 * @since 2019-06-23
 */
public interface UserService extends IService<User> {

    /**
     * 通过accountId查询用户信息
     *
     * @param userId 用户账号ID
     * @return
     */
    User getByUserId(String userId);

    /**
     * 查询账户信息列表
     *
     * @return
     */
    List<User> getUserList();

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    Boolean addUser(User user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    Boolean updateUser(User user);

}
