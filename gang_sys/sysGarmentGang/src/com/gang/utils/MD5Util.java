package com.gang.utils;

import java.security.MessageDigest;

/**
 * md5摘要
 * 
 * @author Yang xt
 * 
 * @Aug 25, 2017
 * 
 */
public class MD5Util {
	
	/**
	 * md5编码
	 * 
	 * @param source 源字符串
	 * @return
	 */
	public static String encrypt(String source) {
		StringBuilder buffer = new StringBuilder();
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(source.getBytes("UTF-8"));

			byte[] str_encoded = md5.digest();
			for (int i = 0; i < str_encoded.length; i++) {
				if ((str_encoded[i] & 0xff) < 0x10) {
					buffer.append("0");
				}
				buffer.append(Long.toString(str_encoded[i] & 0xff, 16));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		System.out.println(encrypt("123"));
	}
}
