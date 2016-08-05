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
		//����threadNum�����߳�
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
		//threadNum��д�߳�
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
		//��ʼ��map
		private void initMap() {
			int len = 10;
			for(int i= 0;i<len;i++){
				map.put(i, i+"");
			}
		}
		//get����
		public String get(int key){
			readLock.lock();
			System.out.println(sdf.format(new Date())+"  "+Thread.currentThread().getName()+"���ڶ�map��key="+key+"�����ݡ�����");
			try{
				String value = map.get(key);
				try {
					Thread.sleep(1000);//һ��ʱ����
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(sdf.format(new Date())+"  "+Thread.currentThread().getName()+"������������Ϊ��"+value);
				return value;
			}finally{
				readLock.unlock();
			}
		}
		
		public void put(int key,String value){
			writeLock.lock();
			try{
				System.out.println(sdf.format(new Date())+"  "+Thread.currentThread().getName()+"���ڽ���ֵ��(key,value)=("+key+","+value+")д��Map��");
				map.put(key, value);
				try {
					Thread.sleep(1000);//һ��ʱ����
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(sdf.format(new Date())+"  "+Thread.currentThread().getName()+"д���ݽ���");
			}finally{
				writeLock.unlock();
			}
		}
	}

}

/*
 * ���н����
2016-08-03 17:34:06  Thread-1���ڶ�map��key=1�����ݡ�����
2016-08-03 17:34:06  Thread-0���ڶ�map��key=1�����ݡ�����
2016-08-03 17:34:07  Thread-1������������Ϊ��1
2016-08-03 17:34:07  Thread-1���ڶ�map��key=2�����ݡ�����
2016-08-03 17:34:07  Thread-0������������Ϊ��1
2016-08-03 17:34:08  Thread-1������������Ϊ��2
2016-08-03 17:34:08  Thread-3���ڽ���ֵ��(key,value)=(1,1)д��Map��
2016-08-03 17:34:09  Thread-3д���ݽ���
2016-08-03 17:34:09  Thread-2���ڽ���ֵ��(key,value)=(1,1)д��Map��
2016-08-03 17:34:10  Thread-2д���ݽ���
2016-08-03 17:34:10  Thread-2���ڽ���ֵ��(key,value)=(2,2)д��Map��
2016-08-03 17:34:11  Thread-2д���ݽ���
2016-08-03 17:34:11  Thread-0���ڶ�map��key=2�����ݡ�����
2016-08-03 17:34:12  Thread-0������������Ϊ��2
2016-08-03 17:34:12  Thread-3���ڽ���ֵ��(key,value)=(2,2)д��Map��
2016-08-03 17:34:13  Thread-3д���ݽ���
 * */
