package com.wrh.readwritelock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServerDemo2 {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	public static void main(String[] args) {
		//创建一个线程数为5的线程池，支持周期执行任务
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
			
		}, 1, 1, TimeUnit.SECONDS);//以1s为间隔重复运行
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		exec.shutdown();
	}

}

/*
 * 运行结果：
2016-14-05 05:14:02  pool-1-thread-1  task is running..
2016-14-05 05:14:05  pool-1-thread-1  task is running..
2016-14-05 05:14:08  pool-1-thread-1  task is running..


从结果可以得到如下结论
1、scheduledWithFixedDelay方法是上一次任务结束到此次任务开始执行的间隔为delay.
从结果可以看出，任务执行的间隔为3s=任务执行时间2s+间隔delay(1s)
*/
