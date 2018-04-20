package com.gang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {
	
	//生成优惠券编码
	public static String createCouponId(String title) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		Date d = new Date();
		String date = sdf.format(d) + ZyxxUtil.generateRandCode(4);
		return title + date;
	}

	//生成普通订单号
	public static String createOrderId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date d = new Date();
		String date = sdf.format(d) + ZyxxUtil.generateRandCode(4);
		return "PT" + date;
	}
	
	//生成红包订单号
	public static String createOrderIdPack() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date d = new Date();
		String date = sdf.format(d) + ZyxxUtil.generateRandCode(4);
		return "HB" + date;
	}
	
	//生成提货订单号
	public static String createOrderIdTh() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date d = new Date();
		String date = sdf.format(d) + ZyxxUtil.generateRandCode(4);
		return "TH" + date;
	}
	
	//生成认筹订单号
	public static String createOrderIdRc() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		Date d = new Date();
		String date = sdf.format(d) + ZyxxUtil.generateRandCode(4);
		return "RC" + date;
	}
	
	//计算会员优惠金额
	public static double countMemberAmount(double discount, double priceCount) {
		double memberAmount = discount * priceCount;
		double discountAmount = priceCount - memberAmount;
		return discountAmount;
	}
	
	//时间转long型
	public static long dateToLong(Date date) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date d = sdf.parse(date);
        return date.getTime()/1000;
	}
	
	//long型转时间型
	public static String longToDate(long l) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(l * 1000);
	}
	
	//计算超时时间
	public static String autoCancelMinutes(int minute) {
		int hour = minute/60;
		String time = "";
		if(hour > 48) {
			Double m = hour + 0.0;
			String format = null;
			Object[] array = null;
			Integer days = (int)(m/24);
			Integer hours = (int)(m-days*24);
			Integer minutes = (int)(m*60-hours*60-days*24*60);
			if(days > 0) {
				format = "%1$,d天%2$,d时%3$,d分";
				array = new Object[] {days,hours,minutes};
			}
			//time = hour/24 + "天";
			time = String.format(format, array);
		} else {
			time = hour + "小时";
		}
		return time;
	}
	
	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(OrderUtil.dateToLong(sdf.parse("2016-02-17 13:21:56")));
//		System.out.println(OrderUtil.dateToLong(sdf.parse("2016-02-16 13:21:56")));
		//System.out.println(OrderUtil.longToDate(1455615370)); 86400秒等于24小时
		//System.out.println(System.currentTimeMillis()/1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		Date d = new Date();
		String date = sdf.format(d);
		System.out.println(date);
	}

}
