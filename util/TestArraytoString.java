package com.onlineordering.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestArraytoString {

	public static void main(String[] args){
		String [] strArray = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		Collections.shuffle(list);
		for(int i = 0; i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
	}
}
