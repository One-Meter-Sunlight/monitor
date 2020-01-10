// 下列 ifdef 块是创建使从 DLL 导出更简单的
// 宏的标准方法。此 DLL 中的所有文件都是用命令行上定义的 VIBSPFORND_EXPORTS
// 符号编译的。在使用此 DLL 的
// 任何其他项目上不应定义此符号。这样，源文件中包含此文件的任何其他项目都会将
// VIBSPFORND_API 函数视为是从 DLL 导入的，而此 DLL 则将用此宏定义的
// 符号视为是被导出的。
#ifdef VIBSPFORND_EXPORTS
#define VIBSPFORND_API __declspec(dllexport)
#else
#define VIBSPFORND_API __declspec(dllimport)
#endif









/*
参数说明
double* m_pdata				时域数据数组
float   samplefrequency		采样频率
int     m_DataLength		数组长度
int		windowtype			窗类型		固定为0
*/







/*
功能：计算位移的峰峰值	
返回是位移峰值			
m_pdata：			时域数组(单位是G)		
samplefrequency:	采样频率		
m_DataLength:		时域数组数据点数
*/
extern "C" VIBSPFORND_API  double _stdcall GetDisPP(double* m_pdata, float samplefrequency, int m_DataLength);

/*
功能：计算位移的有效值	
返回是位移有效值		
m_pdata：			时域数组(单位是G)		
samplefrequency:	采样频率		
m_DataLength:		时域数组数据点数
*/
extern "C" VIBSPFORND_API  double _stdcall GetDisRMS(double* m_pdata, float samplefrequency, int m_DataLength);

/*
功能：计算速度有效值		
m_pdata：			时域数组	
samplefrequency:	采样频率		
m_DataLength:		时域数组数据点数
*/
extern "C" VIBSPFORND_API  double _stdcall GetVelRMS(double* m_pdata, float samplefrequency, int m_DataLength);

/*
功能：计算加速度有效值		
m_pdata：			时域数组	
samplefrequency:	采样频率		
m_DataLength:		时域数组数据点数
*/
extern "C" VIBSPFORND_API  double _stdcall GetAccRMS(double* m_pdata, float samplefrequency, int m_DataLength);

/*
功能：这个是算冲击能量的峰峰值   不管 不会用到
*/
extern "C" VIBSPFORND_API  double _stdcall GetEngPP(double* m_pdata, float samplefrequency, int m_DataLength);

/*
功能：由加速度时域计算速度频谱		
m_timeData：		时域输入数组	
samplefrequency:	采样频率		
m_DataLength:		时域数组数据点数		
VelFreData：		输出速度频率
*/
extern "C" VIBSPFORND_API  double _stdcall FreTrans_a2v(double * VelFreData, double * m_timeData, int m_DataLength, float samplefrequency, int windowtype);


/*
功能：计算包络的时域数组和频域数组	
m_timeData：		时域输入数组	
m_Envelope:			输出包络时域图		
EnvelopeFreData：	输出包络频谱图			
windowtype:			加窗类型 1是矩形窗 2是汉宁窗 3是汉明窗 固定是2			m_DataLength:时域数组数据点数	
*/
extern "C" VIBSPFORND_API  double _stdcall GetEnvelope(double * m_Envelope, double * EnvelopeFreData, double * m_timeData, int windowtype, int m_DataLength);

/*
功能：加速度时域数组 -> 频谱图
m_timeData：		时域输入数组
AccFreData：		保存频谱图的数组
m_DataLength:		时域数组数据点数	
windowtype:			加窗类型 1是矩形窗 2是汉宁窗 3是汉明窗 固定是2			m_DataLength:时域数组数据点数	
*/
extern "C" VIBSPFORND_API  double _stdcall FreTrans_a2a(double * AccFreData, double * m_timeData, int m_DataLength,int windowtype);
















// /*
// UnitTypeID		UnitTypeText
// 1				Acceleration
// 2				Velocity
// 3				Displacement
// 
// MeasurementTypeID	MeasurementTypeText
// 1					RMS
// 2					Peak
// 3					Peak-Peak
// 
// */
// extern "C" VIBSPFORND_API  double _stdcall CalcOverall(double* inputdata, float samplefrequency, int datalength,
// 														int inputUnitType, int outputUnitType,int MeasurementType);
// 
// extern "C" VIBSPFORND_API  double _stdcall GetImpactValue(double* m_pdata, float samplefrequency, int m_DataLength);
// 
// 
// extern "C" VIBSPFORND_API  void _stdcall TransinFre(double* inputdata, float samplefrequency, int datalength,
// 														int inputUnitType, double* outputdata, int outputUnitType);
// /*
// SPC_ID	SPC_Name		Unit	Rmarks
// 1		Mean			time
// 2		RMS				time
// 3		Peak			time
// 4		PeaktoPeak		time
// 5		TimeSTD			time	标准差
// 6		TimeVariance		/	方差
// 7		WaveformIndicator	/	波形指标
// 8		CrestFactor			/	峰值指标/峰值因数
// 9		ImpulseIndicator	/	脉冲指标
// 10		MarginIndicator		/	裕度指标
// 11		Skewness			/	偏斜度
// 12		Kurtosis			/	峭度
// */
// extern "C" VIBSPFORND_API  double _stdcall GetSPCValue(double* m_pdata, int m_DataLength, int SPCType);
