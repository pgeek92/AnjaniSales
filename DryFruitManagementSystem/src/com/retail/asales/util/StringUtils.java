package com.retail.asales.util;

public class StringUtils {
	public static boolean isBlankOrNull(String s)
	{
		if(s==null || s.trim().equals("")){
			return true;
		}else{
			return false;
		}	
	}
}
