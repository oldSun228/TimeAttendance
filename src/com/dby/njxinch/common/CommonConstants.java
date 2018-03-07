package com.dby.njxinch.common;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


public class CommonConstants {

	/**
	 * session存放用户的key标识
	 */
	public static final String USER_SESSION_KEY = "ADMIN_USER_BEAN";//登录账号信息
	/**
	 * session存放用户的identy标识
	 */
	public static final String USER_SESSION_IDENTY = "ADMIN_USER_IDENTY";//登录者的 权限标识
	/**
	 * 用户拥有的权限菜单
	 */
	public static final String USER_MENU_RESOURCE="USER_MENU_RESOURCE";//权限菜单（总的）
	/**
	 * 用户拥有的权限按钮
	 */
	public static final String USER_BUTTON_RESOURCE="USER_BUTTON_RESOURCE";//暂无用
	/**
	 * 菜单资源里的菜单
	 */
	public static final String MENU_RESOURCE="MENU_RESOURCE";//没有任何权限的 总的菜单
	
	static {
		Properties props = new Properties();
		try {
			InputStream in = CommonConstants.class.getClassLoader().getResourceAsStream("common.properties");
			props.load(in);
			URL urlPath = CommonConstants.class.getClassLoader().getResource("common.properties");
			String strPath = urlPath.toString();
//			ROOT_PATH = strPath.substring(5,strPath.indexOf("WEB-INF"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
