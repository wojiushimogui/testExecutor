package com.wrh.readwritelock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask1(), 0);
		timer.schedule(new TimerTask2(), 0);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
	}

}
class TimerTask1 extends TimerTask{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void run() {
		System.out.println(sdf.format(new Date())+"  TimerTask1 begin running...,���д�������߳�Ϊ��"+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);//ģ������ִ��ʱ��
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sdf.format(new Date())+"  TimerTask1 over...,���д�������߳�Ϊ��"+Thread.currentThread().getName());
		
	}
	
}

class TimerTask2 extends TimerTask{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void run() {
		System.out.println(sdf.format(new Date())+"  TimerTask2 begin running...,���д�������߳�Ϊ��"+Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sdf.format(new Date())+"  TimerTask2 over...,���д�������߳�Ϊ��" +Thread.currentThread().getName());
		
	}
	
}

/*output:
2016-08-05 10:44:36  TimerTask1 begin running...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:44:37  TimerTask1 over...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:44:37  TimerTask2 begin running...,���д�������߳�Ϊ��Timer-0
2016-08-05 10:44:39  TimerTask2 over...,���д�������߳�Ϊ��Timer-0

���ۣ�
1��Timer��ʹ�õ���һ���̴߳��е�ִ���ύ������
2�����������ύ��ִ�е���ʱ��ȣ��������ִ��˳�������Ⱥ��й�ϵ��
 * */
