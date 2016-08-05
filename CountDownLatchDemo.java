package com.wrh.readwritelock;

	import java.util.concurrent.CountDownLatch;
	
	public class CountDownLatchDemo {
		private static final int NUM = 10;
		private static CountDownLatch  doneSignal = new CountDownLatch(NUM);
		private static CountDownLatch startSignal = new CountDownLatch(1);
		
		public static void main(String[] args) {
			for(int i=0;i<NUM;i++){
				new Thread(){
	
					@Override
					public void run() {
						try {
							//System.out.println(Thread.currentThread().getName()+"   等待一个signal....");
							startSignal.await();
							System.out.println(Thread.currentThread().getName()+"  is running...");
							doneSignal.countDown();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}.start();
			}
			//模拟为其它线程的运行准备资源，例如，在所有准备从数据库中读数据的线程之前连接好数据库等操作
			init();
			startSignal.countDown();//运行到这里，就会将上面的线程全部激活
			try {
				System.out.println("main线程awaiting....");
				doneSignal.await();//main线程在这里等待，等到上面的所有线程全部执行完毕后
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("main线程又开始运行");
			System.out.println("main线程运行结束");
			
		}
	
		private static void init() {
			System.out.println("main为所有的线程的运行做准备。。。。");
		}
	
	}
	
	
	/*程序运行结果：
main为所有的线程的运行做准备。。。。
main线程awaiting....
Thread-0  is running...
Thread-2  is running...
Thread-4  is running...
Thread-6  is running...
Thread-8  is running...
Thread-1  is running...
Thread-3  is running...
Thread-5  is running...
Thread-7  is running...
Thread-9  is running...
main线程又开始运行
main线程运行结束
	 * */
