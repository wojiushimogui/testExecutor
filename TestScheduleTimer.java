package com.wrh.readwritelock;

import java.util.Timer;

public class TestScheduleTimer {

	public static void main(String[] args) {
		Timer timer = new Timer();
		//1s֮��ʼִ�в�ÿ��1sִ��һ��
		timer.scheduleAtFixedRate(new TimerTask1(), 1000, 1000);
		timer.scheduleAtFixedRate(new TimerTask2(), 1000, 1000);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
	}

}

/*output:
2016-08-05 10:55:54  TimerTask1 begin running...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:55:55  TimerTask1 over...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:55:55  TimerTask2 begin running...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:55:57  TimerTask2 over...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:55:57  TimerTask2 begin running...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:55:59  TimerTask2 over...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:55:59  TimerTask1 begin running...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:56:00  TimerTask1 over...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:56:00  TimerTask1 begin running...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:56:01  TimerTask1 over...,���д�������߳�Ϊ��Timer-0

���ۣ�
1��Timerִ�������ǵ��̵߳ģ��ڲ������������ά����ִ������

2������ʹ����С���㷨���������´�ִ��ʱ����ԽСԽ���ȱ�ִ�У����������ʱʹ�������Ʒ�ֹ�������⡣
����Task1,Task2�������ʼִ��ʱ�䶼Ϊ1000�����ڼ����Ϊ1000ms�����ͬһ����������ִ��������

����뽫ÿ������ִ��һ�Σ����ӳ�ʱ���Ϊ��һ�¼��ɡ�

 * */
