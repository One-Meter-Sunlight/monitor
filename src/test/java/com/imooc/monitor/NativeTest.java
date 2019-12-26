package com.imooc.monitor;

/**
 * 动态链接库调用
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/26 21:51
 */
public class NativeTest {



    static {
        System.load("d:\\VibSPforND.dll");
    }

    public static void main(String[] args) {
        NativeUtil nativeUtil = new NativeUtil();
        double result = nativeUtil.GetEnvelope(new double[10], new double[]{1.1, 1.2, 1.3, 1.4, 1.5}, 5);
        System.out.println(result);
    }
}
