package com.gang.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/*
 功能:		duanxin.cm HTTP接口发送短信
 修改日期:	2014-03-19
 说明:		http://api.duanxin.cm/?ac=send&username=用户账号&password=MD5位32密码&phone=号码&content=内容
 状态:
 100 发送成功
 101 验证失败
 102 短信不足
 103 操作失败
 104 非法字符
 105 内容过多
 106 号码过多
 107 频率过快
 108 号码内容空
 109 账号冻结
 110 禁止频繁单条发送
 111 系统暂定发送
 112 号码不正确
 120 系统升级
 */
public class ShortMessageUtil {

	/**
	 * 发送短信验证码
	 * 
	 * @param phone 手机号码（多个号码间用英文半角逗号分隔）
	 * @param content 短信内容
	 * @return 100成功 101失败
	 */
	public static String sendSMCode(String phone, String content) {
		String resultCode = "-1";
		try {
			// 组装URL
			StringBuffer buffer = new StringBuffer("http://api.momingsms.com/?");
			buffer.append("action=send");
			// 用户名
			buffer.append("&username=70212969");
			// 密码（Xdn_2017的32位MD5小写）
			buffer.append("&password=ce3499f18e9fa3cc3ef169447d031b73");
			// 手机号码（多个号码间用英文半角逗号分隔）
			buffer.append("&phone=").append(phone);
			// 短信内容
			buffer.append("&content=" + URLEncoder.encode(content, "GBK"));

			// 创建url对象
			URL url = new URL(buffer.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 设置请求方式
			connection.setRequestMethod("POST");
			// 发送短信
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			// 读取发送结果
			resultCode = in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCode;
	}

	/**
	 * 发送短信通知
	 * 
	 * @param phone 手机号码（多个号码间用英文半角逗号分隔）
	 * @param content 短信内容
	 * @return 100成功 101失败
	 */
	public static String sendSMNotice(String phone, String content) {
		String resultCode = "-1";
		try {
			// 组装URL
			StringBuffer buffer = new StringBuffer("http://api.duanxin.cm/?");
			buffer.append("action=send");
			// 用户名
			buffer.append("&username=70201714");
			// 密码（senankeji的32位MD5小写）
			buffer.append("&password=83c4a2d1988f5bf90d2b5281b357406d");
			// 手机号码（多个号码间用英文半角逗号分隔）
			buffer.append("&phone=").append(phone);
			// 短信内容
			buffer.append("&content=" + URLEncoder.encode(content, "GBK"));

			// 创建url对象
			URL url = new URL(buffer.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 设置请求方式
			connection.setRequestMethod("POST");
			// 发送短信
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			// 读取发送结果
			resultCode = in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCode;
	}
}
