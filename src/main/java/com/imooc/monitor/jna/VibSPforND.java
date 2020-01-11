package com.imooc.monitor.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

/*
 * JNA
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/29 11:19
 */
public interface VibSPforND extends Library {

    VibSPforND vi = (VibSPforND) Native.load("d:\\VibSPforND", VibSPforND.class);

    /**
     * 包络图/包络频谱图
     */
    double GetEnvelope(double[] m_Envelope, double[] envelopeFreData, double[] m_timeData, int windowtype, int m_DataLength);

    /**
     * 计算位移的峰峰值-不用
     */
    double GetDisPP(double[] m_pdata, double samplefrequency, int m_DataLength);

    /**
     * 计算位移的有效值
     */
    double GetDisRMS(double[] m_pdata, float samplefrequency, int m_DataLength);

    /**
     * 计算速度有效值
     */
    double GetVelRMS(double[] m_pdata, float samplefrequency, int m_DataLength);

    /**
     * 计算加速度有效值
     */
    double GetAccRMS(double[] m_pdata, float samplefrequency, int m_DataLength);

    /**
     * 计算冲击能量的峰峰值-不用
     */
    double GetEngPP(double[] m_pdata, float samplefrequency, int m_DataLength);

    /**
     * 由加速度时域计算速度频谱
     */
    double FreTrans_a2v(double[] velFreData, double[] m_timeData, int m_DataLength, float samplefrequency, int windowtype);

    /**
     * 加速度时域数组 -> 频谱图
     */
    double FreTrans_a2a(double[] accFreData, double[] m_timeData, int m_DataLength, int windowtype);
}
