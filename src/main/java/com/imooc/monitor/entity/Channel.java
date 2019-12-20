package com.imooc.monitor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 通道
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/10 20:50
 */
@Data
public class Channel implements Serializable {

    private static final long serialVersionUID = 7228168119652448346L;

    private String name;

    private String datas;

}
