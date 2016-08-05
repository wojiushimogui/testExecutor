package com.wrh.readwritelock;

import java.util.Timer;

public class TestTimer2 {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask1(), 10);//延迟10ms后执行
		timer.schedule(new TimerTask2(), 0);
		timer.schedule(new TimerTask2(), 5);//延迟5ms
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
	}

}

/*output:
2016-08-05 10:49:55  TimerTask2 begin running...,运行此任务的线程为：Timer-0
2016-08-05 10:49:57  TimerTask2 over...,运行此任务的线程为：Timer-0
2016-08-05 10:49:57  TimerTask2 begin running...,运行此任务的线程为：Timer-0
2016-08-05 10:49:59  TimerTask2 over...,运行此任务的线程为：Timer-0
2016-08-05 10:49:59  TimerTask1 begin running...,运行此任务的线程为：Timer-0
2016-08-05 10:50:00  TimerTask1 over...,运行此任务的线程为：Timer-0

结论：
1、如果提交的任务存在延时，则会加入到任务队列中，并根据延迟时间进行排序执行。
 * */
