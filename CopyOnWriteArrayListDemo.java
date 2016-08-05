package com.wrh.readwritelock;

public class CopyOnWriteArrayListDemo {

	private  static Integer[] array;
	public static void main(String[] args) {
		int len = 3;
		array= new Integer[len];
		for(int i=0;i<len;i++){
			array[i]=i;
		}
		int res = new CopyOnWriteArrayListDemo().get(2);
		System.out.println(res);
	}
	
	
    public Integer get(int index) {
        return get(getArray(), index);
    }
    public Object[] getArray() {
        return array;
    }

    private Integer get(Object[] a, int index) {
    	array=null;
    	//System.out.println(a == array);
        return (Integer) a[index];
    }

}
