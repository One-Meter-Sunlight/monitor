package com.imooc.monitor.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * JNA
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/29 11:19
 */
public interface VibSPforND extends Library {

    VibSPforND vi = (VibSPforND) Native.load("d:\\VibSPforND", VibSPforND.class);

    double GetEnvelope(double[] m_Envelope, double[] m_timeData, int m_DataLength);

    double GetDisPP(double[] m_pdata, double samplefrequency, int m_DataLength);

    double GetEngPP(double[] m_pdata, float samplefrequency, int m_DataLength);

    double FreTrans_a2v(double[] velFreData, double[] m_timeData, int m_DataLength, float samplefrequency, int windowtype);
}
