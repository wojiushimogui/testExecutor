package com.wrh.readwritelock;

public class TestConst {
	
	static class Parent{
		Parent(){
			System.out.println("heihei");
		}
	}
	static class Children extends Parent{
		
	}
	public static void main(String[] args) {
		Parent p = new Children();
	}
}
