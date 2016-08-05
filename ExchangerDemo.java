package com.wrh.readwritelock;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

	private static Exchanger<String> exchanger = new Exchanger<String>();
	public static void main(String[] args) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=0;i<2;i++){
					String str = Integer.toString(i);
					System.out.println(Thread.currentThread().getName()+"交换前的数据为："+str);
					String exchangeRes = null;
					try {
						exchangeRes = exchanger.exchange(str);
						System.out.println(Thread.currentThread().getName()
								+"交换的数据情况为：从"+str+"----->"+exchangeRes);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
			
		}).start();
		new Thread(new Runnable(){

			@Override
			public void run() {
				int start = 10;
				int end = 12;
				for(int i=start;i<end;i++){
					String str = Integer.toString(i);
					System.out.println(Thread.currentThread().getName()+"交换前的数据为："+str);
					String exchangeRes = null;
					try {
						exchangeRes = exchanger.exchange(str);
						System.out.println(Thread.currentThread().getName()
								+"交换的数据情况为：从"+str+"----->"+exchangeRes);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
			
		}).start();;
	}

}
