package com.wrh.readwritelock;

import java.util.Timer;

public class TestTimer2 {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask1(), 10);//�ӳ�10ms��ִ��
		timer.schedule(new TimerTask2(), 0);
		timer.schedule(new TimerTask2(), 5);//�ӳ�5ms
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
	}

}

/*output:
2016-08-05 10:49:55  TimerTask2 begin running...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:49:57  TimerTask2 over...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:49:57  TimerTask2 begin running...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:49:59  TimerTask2 over...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:49:59  TimerTask1 begin running...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:50:00  TimerTask1 over...,���д�������߳�Ϊ��Timer-0

���ۣ�
1������ύ�����������ʱ�������뵽��������У��������ӳ�ʱ���������ִ�С�
 * */
