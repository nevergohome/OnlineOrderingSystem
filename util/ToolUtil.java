package com.onlineordering.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具�?
 * 
 * @author Administrator
 *
 */
public class ToolUtil {
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(date);
	}
	
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(date);
	}

	public final static String LOGINUSER = "loginUser";

	public static String arraytoString(String[] array) {
		StringBuffer s = new StringBuffer();
		if (null == array)
			return s.toString();
		for (int i = 0; i < array.length; i++) {
			s.append(array[i]);
			if (i != array.length - 1)
				s.append(",");
		}
		return s.toString();
	}
	
	public static void main (String [] args){
//		String[] classes = new String[]{"1","2222"};
//		System.out.println(ToolUtil.arraytoString(classes));
		System.out.println(ToolUtil.getCurrentTime());
	}
}
