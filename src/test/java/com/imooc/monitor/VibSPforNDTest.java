package com.imooc.monitor;

import com.imooc.monitor.jna.VibSPforND;

/**
 * 动态链接库调用
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/26 21:51
 */
public class VibSPforNDTest {

    public static void main(String[] args) {
        System.out.println(VibSPforND.vi.GetEnvelope(new double[5],
                new double[]{1.1, 1.2, 1.3, 1.4, 1.5}, 5));

        System.out.println("================");

        System.out.println(VibSPforND.vi.GetDisPP(new double[]{1.1, 1.2, 1.3, 1.4, 1.5}, 1.1, 2));
    }
}
