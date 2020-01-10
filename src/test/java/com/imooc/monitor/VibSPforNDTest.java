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
        double[] param = new double[5];
        double[] param2 = new double[5];
        double value = VibSPforND.vi.GetEnvelope(param, param2,
                new double[]{1.1, 1.2, 1.3, 1.4, 1.5}, 2, 5);
        System.out.println(param[0]);
        System.out.println(param[1]);
        System.out.println(param[2]);
        System.out.println(param[3]);
        System.out.println(param[4]);

    }
}

