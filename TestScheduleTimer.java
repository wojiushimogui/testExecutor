package com.wrh.readwritelock;

import java.util.Timer;

public class TestScheduleTimer {

	public static void main(String[] args) {
		Timer timer = new Timer();
		//1s之后开始执行并每隔1s执行一次
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
2016-08-05 10:55:54  TimerTask1 begin running...,运行此任务的线程为：Timer-0
2016-08-05 10:55:55  TimerTask1 over...,运行此任务的线程为：Timer-0
2016-08-05 10:55:55  TimerTask2 begin running...,运行此任务的线程为：Timer-0
2016-08-05 10:55:57  TimerTask2 over...,运行此任务的线程为：Timer-0
2016-08-05 10:55:57  TimerTask2 begin running...,运行此任务的线程为：Timer-0
2016-08-05 10:55:59  TimerTask2 over...,运行此任务的线程为：Timer-0
2016-08-05 10:55:59  TimerTask1 begin running...,运行此任务的线程为：Timer-0
2016-08-05 10:56:00  TimerTask1 over...,运行此任务的线程为：Timer-0
2016-08-05 10:56:00  TimerTask1 begin running...,运行此任务的线程为：Timer-0
2016-08-05 10:56:01  TimerTask1 over...,运行此任务的线程为：Timer-0

结论：
1、Timer执行任务是单线程的，内部用任务队列来维护待执行任务

2、任务使用最小堆算法排序（任务下次执行时间距今越小越优先被执行），添加任务时使用锁机制防止并发问题。
上述Task1,Task2任务的起始执行时间都为1000，周期间隔都为1000ms。因此同一个任务连续执行了两次

如果想将每个任务执行一次，则将延迟时间改为不一致即可。

 * */
