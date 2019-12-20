package com.imooc.monitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.monitor.dao.UserMapper;
import com.imooc.monitor.entity.User;
import com.imooc.monitor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户管理服务实现类
 *
 * @author kai.chen
 * @since 2019-06-23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过accountId查询用户信息
     *
     * @param userId 用户账号ID
     * @return
     */
    @Override
    public User getByUserId(String userId) {
        return userMapper.selectOneByUserId(userId);
    }

    /**
     * 查询账户信息列表
     *
     * @return
     */
    @Override
    public List<User> getUserList() {
        log.info("查询账户信息列表");
        return userMapper.selectUserList();
    }

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    @Override
    public Boolean addUser(User user) {
        log.info("新增用户信息");
        return userMapper.insert(user) > 0 ? true : false;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateById(user) > 0 ? true : false;
    }

}