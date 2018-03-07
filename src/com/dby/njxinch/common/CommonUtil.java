package com.dby.njxinch.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {

	public static String getDate(int intdate) {
		// double intdates =4294967295;

		return null;
	}

	public static String getDateType(Date date, String format) {
		String datestr = null;
		if (date != null) {
			SimpleDateFormat sFormat = new SimpleDateFormat(format);
			datestr = sFormat.format(date);

		}
		return datestr;
	}
	public static String getDateType2(String str, String format) {
		String datestr = null;
		if (str != null) {
			Long time=new Long(str);
			SimpleDateFormat sFormat = new SimpleDateFormat(format);
			datestr = sFormat.format(time*1000);
		}
		return datestr;
	}
	
	
	
	public static String getCurrentYear(){
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String s_year = year.substring(2,4);
		return s_year;
	}
	
	/**
	 * 
	 * 
	 * @param end_time
	 * @return
	 */
	public static String getSurplusTime(long end_time){
		Long current_time=System.currentTimeMillis();
		String ret = "";
		if(end_time > current_time){
			long time2 = end_time - current_time;
		
			//转换为秒数
			long second=time2/1000; //剩余总秒数
			//将秒数专为天数
			long s = second % 60; // 秒
			long mi = (second - s) / 60 % 60; // 分钟
			long h =  ((second - s) / 60 - mi ) / 60 % 24; // 小时
			long d =  (((second - s) / 60 - mi ) / 60 - h ) / 24; // 天
			
			ret= d + "天" + h + "小时" + mi + "分钟" + s + "秒";
		}else{
			ret= 0 + "天" + 0 + "小时" + 0 + "分钟" + 0 + "秒";
		}
        return ret;
	}

	
	public static void main(String[] args){
	
//		long million = System.currentTimeMillis();
//		long reMillion = Long.valueOf(1450548220)*1000;
//		System.out.println(new Date(reMillion).toLocaleString());
//		long re = reMillion - million;
//		if (re > 0) {// 未到结束时间
//			long day=re/(24*60*60*1000);
//			long hour=(re/(60*60*1000)-day*24);
//			long min=((re/(60*1000))-day*24*60-hour*60);	
//		System.out.println( day + "天" + hour
//					+ "时" + min + "分");
//		} else {// 未到结束时间
//			long re1 = 0 - re;
//			long day=re1/(24*60*60*1000);
//			long hour=(re1/(60*60*1000)-day*24);
//			long min=((re1/(60*1000))-day*24*60-hour*60);
//			long s=(re1/1000-day*24*60*60-hour*60*60-min*60);
//			System.out.println(
//					"已经延期" + day + "天" + hour + "时"
//							+ min + "分");
//		}
	}
}
