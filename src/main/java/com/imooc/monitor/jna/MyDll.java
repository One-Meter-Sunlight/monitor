package com.imooc.monitor.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/29 15:41
 */
public interface MyDll extends Library {

    MyDll vi = (MyDll) Native.load("d:\\MyDll", MyDll.class);

    int Add(int a, int b);
}
