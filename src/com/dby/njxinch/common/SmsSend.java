package com.dby.njxinch.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/*--------------------------------
 功能:		快讯短信 PHP HTTP接口 发送短信
 修改日期:	2014-04-12
 说明:		http://api.106msg.com/TXTJK.aspx?type=send&ua=*****&pwd=*****&gwid=***&mobile=手机号1,手机号2,手机号3&msg=【签名】短信内容 
 状态:

 1   发送短信成功(其他请求代表成功)
 -1  账号无效或未开户
 -2  账号密码错误
 -3  下发手机号为空
 -4  下发短信内容为空
 -5  指定短信企业ID为空
 -6  账户或密码错误
 -7  账户被冻结
 -8  下发短信内容包含非法关键词
 -9  账户没有充值或指定企业ID错误
 -10 下发短信内容长度超出规定限制，限制为350字符
 -11 下发账号余额不足
 -20 服务器连接异常
 -21 当前短信隶属106营销短信 必须加“尊称”、“退订回复T”
 -99 系统未知错误

 --------------------------------*/

public class SmsSend {

	public static String conent = "【 企运宝】尊敬的企运宝用户，您正在进行修改密码操作，本次验证码为%1，5分钟内有效，请妥善保管。";
	public static String conent_zc = "【企运宝】终于等到您！恭喜通过审核，正式入驻企运宝。您的登陆账号是%1，登陆密码是%2，请妥善保管。下载APP www.7yunbao.com ，手机操作更便捷！";
	public static String conent_cz = "【企运宝】尊敬的企运宝用户，您于%1重置了密码，请妥善保管密码，如非本人操作，请致电客服400800";
	public static String conent_sj = "【 企运宝】尊敬的企运宝用户，您正在进行手机验证操作，本次验证码为%1，5分钟内有效，请妥善保管。";
	/**
	 * @param args
	 */
	public static void sendSms(String phone, String message) {
		// HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		Map<String, String> params = new HashMap<String, String>();
		params.put("type", "send");
		params.put("ua", "121607800");
		params.put("pwd", "123456");
		params.put("gwid", "30");
		params.put("mobile", phone);
		params.put("msg", message);
		System.out.println("sendSms message:" + message);

		HttpPost httpPost = SmsSend.postForm(
				"http://api.106msg.com/TXTJK.aspx", params);
		System.out.println(httpPost.getRequestLine());
		try {

			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);

			HttpEntity entity = httpResponse.getEntity();

			System.out.println("status:" + httpResponse.getStatusLine());

			if (entity != null) {
				System.out.println("contentEncoding:"
						+ entity.getContentEncoding());
				System.out.println("response content:"
						+ EntityUtils.toString(entity));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static HttpPost postForm(String url, Map<String, String> params) {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}

	public static void main(String[] args) {
		SmsSend.sendSms("13306149026", SmsSend.conent.replace("%1", "469258"));
	}
}
