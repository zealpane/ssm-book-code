package com.gdatacloud.zz.ssm.utils;

public class StrKit {

	/**
	 * 将传入的类名首字母转为小写
	 * @param str
	 * @return
	 */
	public static String lowerFirst(String str) {
		// 先将字符串分割为字符数组
		char[] chars = str.toCharArray();
		// 判断首字母是否大写，如果是，那么将首字母转为小写
		if (Character.isUpperCase(chars[0])) {			
			chars[0] += 32;
			// 返回转换后的字符串
			return String.valueOf(chars);
		}
		// 如果首字母不是大写字母，那么直接返回
		return str;
	}
}
