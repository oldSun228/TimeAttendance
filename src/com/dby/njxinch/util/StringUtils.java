/**
 * StringUtils.java
 *
 * @Title:
 * @Description:
 * @Copyright:杭州尚尚签网络科技有限公司 Copyright (c) 2014
 * @Company:杭州尚尚签网络科技有限公司
 * @Created on 2014-9-18 下午2:49:56
 * @author lijianhang
 * @version 1.0
 */

/**
 * 
 */
package com.dby.njxinch.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author lijianhang
 *
 */
public final class StringUtils {

	private StringUtils() {
	}

    public static boolean isEmpty(String str) {

    	return str == null || str.trim().length() == 0;
    }

    public static boolean isNotEmpty(String str) {

    	return str != null && str.trim().length() > 0;
    }

    public static String nullToStrTrim(String str) {

    	if (str == null) {
        	return "";
        }

        return str.trim();
    }

	public static boolean checkString(String str, String regex) {

    	return str.matches(regex);
	}

    public static int getLength(String str) {

        if (str == null) {
        	str = "";
        }
        return str.replaceAll("[^\\x00-\\xff]","**").length();
    }

	public static boolean checkMD5(String md5) {

		return checkString(md5, "[0-9A-Fa-f]{32}");
	}

	public static String byte2hex(byte[] b) {

		String str = "";
		String stmp = "";

		int length = b.length;

		for (int n = 0; n < length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				str += "0";
			}
			str += stmp;
		}

		return str.toLowerCase();
	}

	public static boolean checkEmail(String email) {

		email = nullToStrTrim(email);
		if (isEmpty(email)) {
			return false;
		}
		if (email.length() < 6 || email.length() > 50) {
			return false;
		}

		String regex = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
		regex = "^[a-zA-Z0-9_.-]+@(([a-zA-Z0-9-])+.)+(?:com|cn|net)$";
		
		final Pattern pattern = Pattern.compile(regex);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			return false;
		}else{
			return true;
		}
		
//		return email.matches(regex);
	}

	public static boolean checkPhone(String phone) {

		return checkString(phone, "1[0-9]{10}");
	}

	public static boolean checkMobile(String mobile) {

		return checkString(mobile, "1[3,4,5,7,8][0-9]{9}");
	}

}
