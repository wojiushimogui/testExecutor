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
		System.out.println(sdf.format(new Date())+"  TimerTask1 begin running...,运行此任务的线程为："+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);//模拟任务执行时间
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sdf.format(new Date())+"  TimerTask1 over...,运行此任务的线程为："+Thread.currentThread().getName());
		
	}
	
}

class TimerTask2 extends TimerTask{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void run() {
		System.out.println(sdf.format(new Date())+"  TimerTask2 begin running...,运行此任务的线程为："+Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sdf.format(new Date())+"  TimerTask2 over...,运行此任务的线程为：" +Thread.currentThread().getName());
		
	}
	
}

/*output:
2016-08-05 10:44:36  TimerTask1 begin running...,运行此任务的线程为：Timer-0
2016-08-05 10:44:37  TimerTask1 over...,运行此任务的线程为：Timer-0
2016-08-05 10:44:37  TimerTask2 begin running...,运行此任务的线程为：Timer-0
2016-08-05 10:44:39  TimerTask2 over...,运行此任务的线程为：Timer-0

结论：
1、Timer类使用的是一个线程串行的执行提交的任务。
2、两个任务提交的执行的延时相等，则任务的执行顺序和提高先后有关系。
 * */
