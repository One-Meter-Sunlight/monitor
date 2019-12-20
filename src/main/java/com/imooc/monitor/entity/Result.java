package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 公共接口返回对象
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/22 11:30
 */
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 722816719652448346L;

    private Integer code;
    private String message;
}
