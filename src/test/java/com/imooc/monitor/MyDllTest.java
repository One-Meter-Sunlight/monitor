package com.imooc.monitor;

import com.imooc.monitor.jna.MyDll;

/**
 * 动态链接库调用
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/26 21:51
 */
public class MyDllTest {

    public static void main(String[] args) {

        System.out.println(MyDll.vi.Add(1, 5));
    }
}
