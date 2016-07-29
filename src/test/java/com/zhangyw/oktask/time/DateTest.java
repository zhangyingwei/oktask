package com.zhangyw.oktask.time;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DateTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0;i<100;i++){
			list.add(i);
		}
		
		int a = 99;
		Long start = System.currentTimeMillis();
		for(int i = 0;i<100;i++){
			if(list.get(i)==a){
				System.out.println(i);
			}
		}
		System.out.println(System.currentTimeMillis()/1000-start/1000);
	}
}
