package com.controller;

public class TestBean {
	
	public static void main(String a[]) {
		System.out.println(Long.toHexString(Double.doubleToLongBits(Math.random())));
		
		String str = "A.$BB#$BB#$BB#$E";
		String[] arr = str.split("\\.");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
