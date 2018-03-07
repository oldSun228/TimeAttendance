package com.dby.njxinch.common;

import java.util.Random;

public class RandomUtil {

	public static final int PWD_TYPE_NUM = 1;

	public static final int PWD_TYPE_CHAR = 2;

	public static final int PWD_TYPE_NUM_AND_CHAR = 3;


	/**
	 * 生成随即密码
	 * @param pwd_len 生成的密码的总长度
	 * @param str 自定义乱码选择范围
	 * @return 密码的字符串
	 */
	public static String genRandomNum(int pwd_len, char[] str) {
		return exeRandom(pwd_len, str);
	}
	
	public static String genRandomNum(int pwd_len){
		return genRandomNum(pwd_len, PWD_TYPE_NUM_AND_CHAR);
	}
	
	/**
	 * 生成随即密码
	 * 
	 * @param pwd_len
	 *            生成的密码的总长度
	 * @param type 密码选择类型
	 * @return 密码的字符串
	 */
	public static String genRandomNum(int pwd_len, int type) {
		char[] str = null;
		switch (type) {
		case PWD_TYPE_NUM:
			str = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
			break;
		case PWD_TYPE_CHAR:
			str = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
					'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
					'v', 'w', 'x', 'y', 'z' };
			break;
		case PWD_TYPE_NUM_AND_CHAR:
			str = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
					'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
					'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
					'7', '8', '9' };
			break;
		default:
			str = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
					'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
					'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
					'7', '8', '9' };
		}
		return exeRandom(pwd_len, str);
	}

	private static String exeRandom(int pwd_len, char[] str) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = str.length;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

}
