package com.wrh.readwritelock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) {
		int threadNum = 2;
		ReadWriteLockDemo demo = new ReadWriteLockDemo();
		RWMap rwMap = demo.new RWMap();
		//开启threadNum个读线程
		for(int i=0;i<threadNum;i++){
			new Thread(){
				@Override
				public void run() {
					int j = 0;
					while(j++<2){
						rwMap.get(j);
					}
					
				}
				
			}.start();;
			
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//threadNum个写线程
		for(int i=0;i<threadNum;i++){
			new Thread(){
				@Override
				public void run() {
					int j = 0;
					while(j++<2){
						rwMap.put(j,j+"");
					}
					
				}
				
			}.start();;
			
		}
		
		
	}
	
	class RWMap{
		
		private Map<Integer,String> map; 
		private ReadWriteLock rwLock ;
		private Lock readLock ;
		private Lock writeLock ;
		public RWMap(){
			map = new HashMap<Integer,String>();
			rwLock = new ReentrantReadWriteLock();
			readLock = rwLock.readLock();
			writeLock = rwLock.writeLock();
			initMap();
		}
		//初始化map
		private void initMap() {
			int len = 10;
			for(int i= 0;i<len;i++){
				map.put(i, i+"");
			}
		}
		//get方法
		public String get(int key){
			readLock.lock();
			System.out.println(sdf.format(new Date())+"  "+Thread.currentThread().getName()+"正在读map中key="+key+"的数据。。。");
			try{
				String value = map.get(key);
				try {
					Thread.sleep(1000);//一定时间间隔
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(sdf.format(new Date())+"  "+Thread.currentThread().getName()+"读的数据内容为："+value);
				return value;
			}finally{
				readLock.unlock();
			}
		}
		
		public void put(int key,String value){
			writeLock.lock();
			try{
				System.out.println(sdf.format(new Date())+"  "+Thread.currentThread().getName()+"正在将键值对(key,value)=("+key+","+value+")写入Map中");
				map.put(key, value);
				try {
					Thread.sleep(1000);//一定时间间隔
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(sdf.format(new Date())+"  "+Thread.currentThread().getName()+"写数据结束");
			}finally{
				writeLock.unlock();
			}
		}
	}

}

/*
 * 运行结果：
2016-08-03 17:34:06  Thread-1正在读map中key=1的数据。。。
2016-08-03 17:34:06  Thread-0正在读map中key=1的数据。。。
2016-08-03 17:34:07  Thread-1读的数据内容为：1
2016-08-03 17:34:07  Thread-1正在读map中key=2的数据。。。
2016-08-03 17:34:07  Thread-0读的数据内容为：1
2016-08-03 17:34:08  Thread-1读的数据内容为：2
2016-08-03 17:34:08  Thread-3正在将键值对(key,value)=(1,1)写入Map中
2016-08-03 17:34:09  Thread-3写数据结束
2016-08-03 17:34:09  Thread-2正在将键值对(key,value)=(1,1)写入Map中
2016-08-03 17:34:10  Thread-2写数据结束
2016-08-03 17:34:10  Thread-2正在将键值对(key,value)=(2,2)写入Map中
2016-08-03 17:34:11  Thread-2写数据结束
2016-08-03 17:34:11  Thread-0正在读map中key=2的数据。。。
2016-08-03 17:34:12  Thread-0读的数据内容为：2
2016-08-03 17:34:12  Thread-3正在将键值对(key,value)=(2,2)写入Map中
2016-08-03 17:34:13  Thread-3写数据结束
 * */
