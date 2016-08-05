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
							//System.out.println(Thread.currentThread().getName()+"   �ȴ�һ��signal....");
							startSignal.await();
							System.out.println(Thread.currentThread().getName()+"  is running...");
							doneSignal.countDown();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}.start();
			}
			//ģ��Ϊ�����̵߳�����׼����Դ�����磬������׼�������ݿ��ж����ݵ��߳�֮ǰ���Ӻ����ݿ�Ȳ���
			init();
			startSignal.countDown();//���е�����ͻὫ������߳�ȫ������
			try {
				System.out.println("main�߳�awaiting....");
				doneSignal.await();//main�߳�������ȴ����ȵ�����������߳�ȫ��ִ����Ϻ�
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("main�߳��ֿ�ʼ����");
			System.out.println("main�߳����н���");
			
		}
	
		private static void init() {
			System.out.println("mainΪ���е��̵߳�������׼����������");
		}
	
	}
	
	
	/*�������н����
mainΪ���е��̵߳�������׼����������
main�߳�awaiting....
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
main�߳��ֿ�ʼ����
main�߳����н���
	 * */
