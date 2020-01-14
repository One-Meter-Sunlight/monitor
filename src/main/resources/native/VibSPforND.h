// ���� ifdef ���Ǵ���ʹ�� DLL �������򵥵�
// ��ı�׼�������� DLL �е������ļ��������������϶���� VIBSPFORND_EXPORTS
// ���ű���ġ���ʹ�ô� DLL ��
// �κ�������Ŀ�ϲ�Ӧ����˷��š�������Դ�ļ��а������ļ����κ�������Ŀ���Ὣ
// VIBSPFORND_API ������Ϊ�Ǵ� DLL ����ģ����� DLL ���ô˺궨���
// ������Ϊ�Ǳ������ġ�
#ifdef VIBSPFORND_EXPORTS
#define VIBSPFORND_API __declspec(dllexport)
#else
#define VIBSPFORND_API __declspec(dllimport)
#endif









/*
����˵��
double* m_pdata				ʱ����������
float   samplefrequency		����Ƶ��
int     m_DataLength		���鳤��
int		windowtype			������		�̶�Ϊ0
*/







/*
���ܣ�����λ�Ƶķ��ֵ	
������λ�Ʒ�ֵ			
m_pdata��			ʱ������(��λ��G)		
samplefrequency:	����Ƶ��		
m_DataLength:		ʱ���������ݵ���
*/
extern "C" VIBSPFORND_API  double _stdcall GetDisPP(double* m_pdata, float samplefrequency, int m_DataLength);

/*
���ܣ�����λ�Ƶ���Чֵ	
������λ����Чֵ		
m_pdata��			ʱ������(��λ��G)		
samplefrequency:	����Ƶ��		
m_DataLength:		ʱ���������ݵ���
*/
extern "C" VIBSPFORND_API  double _stdcall GetDisRMS(double* m_pdata, float samplefrequency, int m_DataLength);

/*
���ܣ������ٶ���Чֵ		
m_pdata��			ʱ������	
samplefrequency:	����Ƶ��		
m_DataLength:		ʱ���������ݵ���
*/
extern "C" VIBSPFORND_API  double _stdcall GetVelRMS(double* m_pdata, float samplefrequency, int m_DataLength);

/*
���ܣ�������ٶ���Чֵ		
m_pdata��			ʱ������	
samplefrequency:	����Ƶ��		
m_DataLength:		ʱ���������ݵ���
*/
extern "C" VIBSPFORND_API  double _stdcall GetAccRMS(double* m_pdata, float samplefrequency, int m_DataLength);

/*
���ܣ���������������ķ��ֵ   ���� �����õ�
*/
extern "C" VIBSPFORND_API  double _stdcall GetEngPP(double* m_pdata, float samplefrequency, int m_DataLength);

/*
���ܣ��ɼ��ٶ�ʱ������ٶ�Ƶ��		
m_timeData��		ʱ����������	
samplefrequency:	����Ƶ��		
m_DataLength:		ʱ���������ݵ���		
VelFreData��		����ٶ�Ƶ��
*/
extern "C" VIBSPFORND_API  double _stdcall FreTrans_a2v(double * VelFreData, double * m_timeData, int m_DataLength, float samplefrequency, int windowtype);


/*
���ܣ���������ʱ�������Ƶ������	
m_timeData��		ʱ����������	
m_Envelope:			�������ʱ��ͼ		
EnvelopeFreData��	�������Ƶ��ͼ			
windowtype:			�Ӵ����� 1�Ǿ��δ� 2�Ǻ����� 3�Ǻ����� �̶���2			m_DataLength:ʱ���������ݵ���	
*/
extern "C" VIBSPFORND_API  double _stdcall GetEnvelope(double * m_Envelope, double * EnvelopeFreData, double * m_timeData, int windowtype, int m_DataLength);

/*
���ܣ����ٶ�ʱ������ -> Ƶ��ͼ
m_timeData��		ʱ����������
AccFreData��		����Ƶ��ͼ������
m_DataLength:		ʱ���������ݵ���	
windowtype:			�Ӵ����� 1�Ǿ��δ� 2�Ǻ����� 3�Ǻ����� �̶���2			m_DataLength:ʱ���������ݵ���	
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
// 5		TimeSTD			time	��׼��
// 6		TimeVariance		/	����
// 7		WaveformIndicator	/	����ָ��
// 8		CrestFactor			/	��ֵָ��/��ֵ����
// 9		ImpulseIndicator	/	����ָ��
// 10		MarginIndicator		/	ԣ��ָ��
// 11		Skewness			/	ƫб��
// 12		Kurtosis			/	�Ͷ�
// */
// extern "C" VIBSPFORND_API  double _stdcall GetSPCValue(double* m_pdata, int m_DataLength, int SPCType);
