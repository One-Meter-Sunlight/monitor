package com.imooc.monitor;

/**
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/26 21:54
 */
public class NativeUtil {

    public native double GetEnvelope(double[] m_Envelope, double[] m_timeData, int m_DataLength);
}
