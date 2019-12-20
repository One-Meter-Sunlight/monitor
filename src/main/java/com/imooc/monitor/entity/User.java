package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/22 11:30
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1854427573147314618L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 账户名
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 数据源
     */
    private String dataSource;

    /**
     * 创建时间
     */
    private Date created;
}
