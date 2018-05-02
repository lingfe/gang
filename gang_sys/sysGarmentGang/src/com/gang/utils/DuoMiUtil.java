package com.gang.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DuoMiUtil {
	/**
	 * 项目的访问路径
	 */
	public static String basePath = "http://www.shang9.top/sjgj_wx";
//	public static String basePath = "http://fdx.ngrok.4kb.cn/sjgj_wx";

	/**
	 * 公众号ID
	 */
	public static String appId = "wx89539fc076fbea9b";
	// 测试
//	public static String appId = "wx466b2637fef70af2";

	/**
	 * 公众号密钥
	 */
	public static String appSecret = "8a2b19a5a42d1dbe6b2a10e740cfc196";
	// 测试
//	public static String appSecret = "313aa9c8ba7bf1fc8ea8a181c5922d14";
	
	/**
	 * 商户号
	 */
	public static String partner = "1381247802";
	// 多米的
//	public static String partner = "1320476601";
	/**
	 * API密钥（交易过程中生成签名的密钥） 微信商户平台-账户设置-API安全-设置密钥
	 */
	public static String partnerkey = "692e05de5f15f711c8ab7a92149958aa";
	// 多米的
//	public static String partnerkey = "22f7c381a0c13bb494d3bed2b37ecb9e";

	/**
	 * 欢迎语
	 */
	public static String WELCOME = "欢迎关注民大先锋微信公众平台，在这里您可以了解时政要闻、学习园地、我的党员信息、党费查询、缴费提醒...等等，随时随地互动交流！";

	/**
	 * 应用程序中使用的access_token
	 */
	// public static String accessToken = null;

	/**
	 * scope为snsapi_base
	 */
	public static String oauth2BaseUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
			+ "&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

	/**
	 * scope为snsapi_userinfo
	 */
	public static String oauth2UserInfoUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
			+ "&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	public static String crrServerIp = "121.43.33.90";

	/**
	 * 得到本周周一的日期
	 * 
	 * @return yyyy-MM-dd
	 */
	public static String getMondayOfThisWeek() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		// 得到今天是星期几
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		// 对星期天的特殊处理
		if (dayOfWeek == 0)
			dayOfWeek = 7;
		// 假如今天是星期四，往前推3天就是星期一，即 -（4-1）
		c.add(Calendar.DATE, -(dayOfWeek - 1));
		return df.format(c.getTime());
	}

	/**
	 * 如果目标字符串是null，则转换成""
	 * 
	 * @param source
	 * @return
	 */
	public static String nullToEmptyStr(String source) {
		return (null == source) ? "" : source;
	}

	/**
	 * 生成指定位数的随机数
	 * 
	 * @param codeLen(位数)
	 * @return
	 */
	public static String generateRandCode(int codeLen) {
		int count = 0;
		char str[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer randCode = new StringBuffer();
		Random random = new Random();
		while (count < codeLen) {
			int i = Math.abs(random.nextInt(10));
			if ((i >= 0) && (i < str.length)) {
				randCode.append(str[i]);
				count++;
			}
		}
		return randCode.toString();
	}

	/**
	 * 获取当前日期(yyyy-MM-dd)
	 * 
	 * @return
	 */
	public static String getStdDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

	/**
	 * 求两个列表的差集 diff(list1, list2)
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List<String> diffList(List<String> list1, List<String> list2) {
		List<String> resultList = new ArrayList<String>(Arrays.asList(new String[list1.size()]));
		Collections.copy(resultList, list1);
		resultList.removeAll(list2);
		return resultList;
	}

	public static String getRandomFileName() {
		return getTimestamp() + generateRandCode(3);
	}

	/**
	 * 得到当前时间戳
	 * 
	 * @return
	 */
	public static String getTimestamp() {
		// 17位的时间
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return df.format(new Date());
	}
	
	/**
	 * 时间转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStringTwo(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(date);
	}
	
	/**
	 * list中去重
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	 public static List removeDuplicateWithOrder(List list){
		Set set = new HashSet();  
	      List newList = new ArrayList();  
	   for (Iterator iter = list.iterator(); iter.hasNext();) {  
	          Object element = iter.next();  
	          if (set.add(element))  
	             newList.add(element);  
	       }   
	      list.clear();  
	      list.addAll(newList);  
		return list;
    }
	/**
	 * 数组中去重
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public static String[] array_unique(String[] a) {  
	    // array_unique  
	    List<String> list = new LinkedList<String>();  
	    for(int i = 0; i < a.length; i++) {  
	        if(!list.contains(a[i])) {  
	            list.add(a[i]);  
	        }  
	    }  
	    return list.toArray(new String[list.size()]);  
	}  

	// 转换日期
	public static String fmatDateType() {
		Date d = new Date();
		String dateFmat = new SimpleDateFormat("yyyyMMdd").format(d);
		return dateFmat;
	}
	
	// 转换日期格式 例如 2016-03-02
	public static Date parseGoodsInfoDateType(String dateStr) throws ParseException {
		Date dateFmat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateStr);
		return dateFmat;
	}
	
	/**
	 * 时间格式转换 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String farmtDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		return str;
	}

	/**
	 * 时间格式转换 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static Date farmtStringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date str = null;
		try {
			str = sdf.parse(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 时间格式转换 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String farmtDateDayNoZero(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		String str = sdf.format(date);
		return str;
	}
	
	/**
	 * 获取当前日期yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDateYYYYMMdd() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());
	}
	
	/**
	 * 获取今日的前7天日期
	 * 
	 * @param date
	 * @param daynum
	 * @return
	 */
	public static String[] getDateBefore(Date date, int daynum) {
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		Date DateBefore = null;
		String[] DateBefores = new String[daynum];
		for (int i = daynum; i >= 1; i--) {
			now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
			DateBefore = now.getTime();
			DateBefores[i - 1] = sdf.format(DateBefore);
		}

		// return JSONArray.fromObject(DateBefores).toString();
		return DateBefores;
	}
	
	// 四舍五入保留两位小数
	public static double getTwoDouble(double f) {
		BigDecimal b = new BigDecimal(f);  
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	/**
	 * java属性转sql字段
	 * 
	 * @param javaAttr
	 * @return
	 */
	public static String javaAttrToSqlField(String javaAttr) {
		char[] javaArr = javaAttr.toCharArray();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < javaArr.length; i++) {
			if (javaArr[i] >= 'a' && javaArr[i] <= 'z') {
				buffer.append(String.valueOf(javaArr[i]));
			}
			else if (javaArr[i] >= 'A' && javaArr[i] <= 'Z') {
				buffer.append("_" + String.valueOf(javaArr[i]).toLowerCase());
			}
			else {
				buffer.append(String.valueOf(javaArr[i]));
			}
		}

		return buffer.toString();
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,2,3,4,3};
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				if(a[i] == a[j]) {
					System.out.println(i);
				}
			}
			
		}

		
	}
}
