package com.wrh.readwritelock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServerDemo2 {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	public static void main(String[] args) {
		//����һ���߳���Ϊ5���̳߳أ�֧������ִ������
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
		exec.scheduleWithFixedDelay(new Runnable(){

			@Override
			public void run() {
				try {
					System.out.println(sdf.format(new Date())+"  "+Thread.currentThread().getName()+"  task is running..");
					
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				};
				
			}
			
		}, 1, 1, TimeUnit.SECONDS);//��1sΪ����ظ�����
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		exec.shutdown();
	}

}

/*
 * ���н����
2016-14-05 05:14:02  pool-1-thread-1  task is running..
2016-14-05 05:14:05  pool-1-thread-1  task is running..
2016-14-05 05:14:08  pool-1-thread-1  task is running..


�ӽ�����Եõ����½���
1��scheduledWithFixedDelay��������һ������������˴�����ʼִ�еļ��Ϊdelay.
�ӽ�����Կ���������ִ�еļ��Ϊ3s=����ִ��ʱ��2s+���delay(1s)
*/
