package com.wrh.readwritelock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServerDemo {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	public static void main(String[] args) {
		//创建一个线程数为5的线程池，支持周期执行任务
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
			
		}, 1, 1, TimeUnit.SECONDS);//以1s为周期来运行此任务
		
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
2016-07-05 05:07:34  pool-1-thread-1  task is running..
2016-07-05 05:07:36  pool-1-thread-1  task is running..
2016-07-05 05:07:38  pool-1-thread-2  task is running..
2016-07-05 05:07:40  pool-1-thread-2  task is running..
2016-07-05 05:07:42  pool-1-thread-3  task is running..

从结果可以得到如下结论
1、ScheduledExecutorService支持多线程来同时处理任务
2、当任务的运行时间比周期长时，则将推迟后续执行，但不会同时执行。
这里的运行时间为2s，而周期为1s。结果中任务执行的间隔为2s就可以说明这一点
*/
