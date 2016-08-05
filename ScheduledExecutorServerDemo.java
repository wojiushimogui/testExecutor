package com.wrh.readwritelock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServerDemo {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	public static void main(String[] args) {
		//����һ���߳���Ϊ5���̳߳أ�֧������ִ������
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
		exec.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				try {
					System.out.println(sdf.format(new Date())+"  "+Thread.currentThread().getName()+"  task is running..");
					
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				};
				
			}
			
		}, 1, 1, TimeUnit.SECONDS);//��1sΪ���������д�����
		
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
2016-07-05 05:07:34  pool-1-thread-1  task is running..
2016-07-05 05:07:36  pool-1-thread-1  task is running..
2016-07-05 05:07:38  pool-1-thread-2  task is running..
2016-07-05 05:07:40  pool-1-thread-2  task is running..
2016-07-05 05:07:42  pool-1-thread-3  task is running..

�ӽ�����Եõ����½���
1��ScheduledExecutorService֧�ֶ��߳���ͬʱ��������
2�������������ʱ������ڳ�ʱ�����Ƴٺ���ִ�У�������ͬʱִ�С�
���������ʱ��Ϊ2s��������Ϊ1s�����������ִ�еļ��Ϊ2s�Ϳ���˵����һ��
*/
