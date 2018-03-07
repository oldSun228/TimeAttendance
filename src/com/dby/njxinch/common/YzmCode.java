package com.dby.njxinch.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class YzmCode extends Thread {

	private static YzmCode instance;

	private static BigDecimal currentdecimal;
	static {
		getInstance();
	}

	public static YzmCode getInstance() {
		if (instance == null) {
			instance = new YzmCode();
			currentdecimal = new BigDecimal(1);
			instance.start();
			return instance;
		} else {
			return instance;
		}
	}

	private static HashMap<String, String> codeMap = new HashMap<String, String>();

	public static String getyzm(String phone) {
		String randstring = RandomUtil.genRandomNum(6, 1);
		synchronized (randstring) {
			codeMap.put(phone, randstring + "-" + currentdecimal.longValue());
		}

		return randstring;
	}

	public static boolean get(String phone, String yzcode) {
		String code = "";
		synchronized (codeMap) {
			code = codeMap.get(phone);
		}
		if (code != null && !"".equals(code)) {
			String[] codestr = code.split("-");
			if (yzcode.equals(codestr[0])) {
				synchronized (codeMap) {
					codeMap.remove(phone);
				}
				return true;
			} else {

				return false;
			}
		} else {

			return false;
		}
	}

	@Override
	public void run() {

		try {
			while (true) {
				synchronized (codeMap) {

					Thread.sleep(1000);
					Iterator<Entry<String, String>> iter = codeMap.entrySet()
							.iterator();
					while (iter.hasNext()) {
						Entry<String, String> e = iter.next();
						String key = e.getKey();
						String value = e.getValue();
						String[] keystrs = value.split("-");
						BigDecimal keybig = new BigDecimal(
								Integer.valueOf(keystrs[1]))
								.add(new BigDecimal(300));
						if (currentdecimal.doubleValue() >= keybig
								.doubleValue()) {
							codeMap.remove(key);
						}
					}
					currentdecimal.add(new BigDecimal(1));
				}
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
