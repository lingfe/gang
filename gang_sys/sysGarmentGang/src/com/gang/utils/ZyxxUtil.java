package com.gang.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

/**
 * 工具类
 * 
 * @author Yang xt
 * 
 * @Aug 25, 2017
 * 
 */
public class ZyxxUtil {

	/**
	 * 当前页码0
	 */
	public static final int CURRENT_PAGE = 0;
	public static final int CURRENT_PAGE1 = 1;
	
	// 页码大小
	public static int PAGE_SIZE = 20;

	/**
	 * 格式化时间字符串
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String fmtStr2Date(String dateStr) {
		Date date = dateStrToDate(dateStr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String str = sdf.format(date);
		return str;
	}

	/**
	 * "yyyy
	 * 
	 * @param date
	 * @return
	 */
	public static String fmtCurrentYear(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String str = sdf.format(date);
		return str;
	}

	/**
	 * 32位MD5加密算法
	 * 
	 * @param data
	 * @return
	 */
	public static String encodeMd5(String data) {
		if (null != data) {
			return DigestUtils.md5Hex(data);
		}
		return "";
	}

	/**
	 * base64加密
	 * 
	 * @param plainText
	 * @return
	 */
	public static String encodeStr(String plainText) {
		byte[] b = plainText.getBytes();
		Base64 base64 = new Base64();
		b = base64.encode(b);
		return new String(b);
	}

	/**
	 * base64 解密
	 * 
	 * @param encodeStr
	 * @return
	 */
	public static String decodeStr(String encodeStr) {
		byte[] b = encodeStr.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		return new String(b);
	}

	/**
	 * "yyyyMMddHHmmss"型时间字符串转换成Date
	 * 
	 * @param date
	 * @return
	 */
	public static Date dateStrToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date finalDate = null;
		try {
			finalDate = sdf.parse(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return finalDate;
	}

	/**
	 * "yyyyMMddHHmm
	 * 
	 * @param date
	 * @return
	 */
	public static String farmtDateMin() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String str = sdf.format(new Date());
		return str;
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
	 * 获得年份
	 * 
	 * @param date
	 * @return
	 */
	public static String getCurrentYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String str = sdf.format(new Date());
		return str;
	}

	/**
	 * 时间格式转换 yyyy-MM-dd
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
	public static Date farmtStringToDateTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	 * 时间格式转换 yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String farmtDateDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		return str;
	}

	/**
	 * 时间格式转换 yyyy年MM月dd日
	 * 
	 * @param date
	 * @return
	 */
	public static String farmtDateDay2(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String str = sdf.format(date);
		return str;
	}

	/**
	 * 得到明天日期 yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getAddDay(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		return sf.format(c.getTime());
	}

	/**
	 * long转为yyyy-MM-dd HH:mm:ss时间格式
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static String timeStampToString(long timeStamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(timeStamp);
		return date;
	}

	/**
	 * 保留两位小数
	 * 
	 * @param number
	 * @return
	 */
	public static String fmatDouble2(double number) {
		DecimalFormat df = new DecimalFormat(".00");
		return df.format(number);
	}

	/**
	 * 生成订单号 项目名 + yyyyMMdd + System.currentTimeMillis()
	 * 
	 * @return
	 */
	public static String createOrderId(String tagOrderName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		String date = sdf.format(d).concat(String.valueOf(System.currentTimeMillis()));
		return tagOrderName.concat(date);
	}

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
	 * 生成指定位数的随机数 0 - 9
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
	 * 获取当前日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getStdDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

	/**
	 * 获取当前日期yyyy-MM-dd HH:mm
	 * 
	 * @return
	 */
	public static String getDateYYYYMMddHHMM() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(new Date());
	}

	/**
	 * 获取当前年月yyyy-MM
	 * 
	 * @return
	 */
	public static String getDateYYYYMM() {
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		return df.format(new Date());
	}

	/**
	 * 获取前一个月的年月yyyy-MM
	 * 
	 * @return
	 */
	public static String getDateYYYYMMLast() {
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		// 过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = df.format(m);
		return mon;
	}

	/**
	 * 获取当前年月往后推一月
	 * 
	 * @return
	 */
	public static String getNextYear() {
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		String currentMonth = df.format(new Date());
		// 往后推一月
		int m = Integer.parseInt(currentMonth.substring(6, 7));
		if (m < 12) {
			m += 1;
		}
		else {
			m = 1;
		}
		String month = "";
		if (m < 10) {
			month = "0" + m;
		}
		else {
			month = "" + m;
		}
		month = currentMonth.substring(0, 5) + month;
		return month;
	}

	/**
	 * yyyy-MM 转成 yyyy-M月
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateFormat(String date) {
		int m = Integer.parseInt(date.substring(6, 7));
		String d = date.substring(0, 5) + m + "月";
		return d;
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

	/**
	 * 获取用户头像
	 * 
	 * @param imageUrl 头像url
	 * @param savePath 保存路径
	 * @param fileName 文件名
	 * @return
	 */
	public static boolean getHeadImage(String imageUrl, String savePath, String fileName) {
		boolean result = false;
		try {
			// 如果保存目录不存在则创建
			File savePathFile = new File(savePath);
			if (!savePathFile.exists()) {
				savePathFile.createNewFile();
			}

			URL url = new URL(imageUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			File qrcodeFile = new File(savePath, fileName);
			FileOutputStream fos = new FileOutputStream(qrcodeFile);
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			result = true;
		}
		catch (Exception e) {
			result = false;
		}
		return result;
	}

	/**
	 * 得到当前时间戳yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */
	public static String getTimestamp() {
		// 17位的时间
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return df.format(new Date());
	}

	/**
	 * 随机字符窜，时间戳 + 随机字符
	 * 
	 * @return
	 */
	public static String getRandomFileName() {
		return getTimestamp().concat(generateRandCode(3));
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath 要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public static boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		}
		else {
			// 判断是否为文件
			// if (file.isFile()) {
			// 为文件时调用删除文件方法
			flag = file.delete();
			// }
		}
		return flag;
	}

	/**
	 * 比较两个时间大小 startTime > endTime 0 startTime < endTime 1 startTime == endTime -1
	 */
	public static int compareDateTime(String startTime, String endTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int flag = 0;
		try {
			Date startDate = df.parse(startTime);
			Date endDate = df.parse(endTime);
			if (startDate.getTime() > endDate.getTime()) {
				flag = 0;
			}
			else if (startDate.getTime() < endDate.getTime()) {
				flag = 1;
			}
			else if (startDate.getTime() == endDate.getTime()) {
				flag = -1;
			}
		}
		catch (Exception exception) {
		}
		return flag;
	}

	/**
	 * 比较两个时间大小 startTime > endTime 0 startTime < endTime 1 startTime == endTime -1
	 */
	public static int compareDate(String startTime, String endTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int flag = 0;
		try {
			Date startDate = df.parse(startTime);
			Date endDate = df.parse(endTime);
			if (startDate.getTime() > endDate.getTime()) {
				flag = 0;
			}
			else if (startDate.getTime() < endDate.getTime()) {
				flag = 1;
			}
			else if (startDate.getTime() == endDate.getTime()) {
				flag = -1;
			}
		}
		catch (Exception exception) {
		}
		return flag;
	}

	/**
	 * 将身份证号替换为*
	 * 
	 * @param idNumber
	 * @return
	 */
	public static String replaceHiden2String(String idNumber) {
		Pattern p = Pattern.compile("(\\w{4})(\\w+)(\\w{3})");
		Matcher m = p.matcher(idNumber);
		return m.replaceAll("$1***********$3");
	}

	/**
	 * 将身份证号替换为*
	 * 
	 * @param idNumber
	 * @return
	 */
	public static String replacePhoneHiden2String(String idNumber) {
		Pattern p = Pattern.compile("(\\w{4})(\\w+)(\\w{3})");
		Matcher m = p.matcher(idNumber);
		return m.replaceAll("$1****$3");
	}

	/**
	 * 将字符串替换为*
	 * 
	 * @param idNumber
	 * @return
	 */
	public static String replaceIndex(String data, int startIndex, int endIndex) {
		String res = "";
		if (2 < data.length()) {
			Pattern p = Pattern.compile("(\\w{" + startIndex + "})(\\w+)(\\w{" + endIndex + "})");
			Matcher m = p.matcher(data);
			res = m.replaceAll("$1***$3");
		}
		return res;
	}

	/**
	 * 指定日期多少月后日期
	 * 
	 * @param xMonth 多少月份
	 * @param startDate 其实时间
	 * @return
	 */
	public static Date xMonthAfterDateTime(int xMonth, Date startDate) {
		// 创建实例
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		// X个月后的日期
		calendar.add(Calendar.MONTH, xMonth);
		// X个月后的日期（Date类型）
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 判断浏览器类型
	 * 
	 * @param request android/ios/
	 * @return
	 */
	public static String flagBrowerType(HttpServletRequest request) {
		String browser = "";
		String userAgent = request.getHeader("User-Agent").toLowerCase();
		if (userAgent.contains("android")) {
			browser = "android";
		}
		else if (userAgent.contains("iphone") || userAgent.contains("ipad") || userAgent.contains("ipod") || userAgent.contains("ios")) {
			browser = "ios";
		}
		return browser;
	}

	/**
	 * 获取当前时间戳（秒）
	 * 
	 * @return
	 */
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取相应日历日期
	 * 
	 * @param date 指定日期
	 * @param xxDay 今天 0 上x天 -x 下x天 x
	 * @return
	 */
	public static List<String> getXXYear(Date date, int xxDay) {
		// 年份list
		List<String> list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i > -10; i--) {
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, i);
			int year = calendar.get(Calendar.YEAR);
			list.add("" + year);
		}
		return list;
	}

	/**
	 * 获取相应日历日期
	 * 
	 * @param date 指定日期
	 * @param xxDay 今天 0 上x天 -x 下x天 x
	 * @return
	 */
	public static List<String> getXXYears(Date date, int xxDay) {
		// 年份list
		List<String> list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		// 往前推X年
		if (xxDay < 0) {
			for (int i = 0; i > xxDay; i--) {
				calendar.setTime(date);
				calendar.add(Calendar.YEAR, i);
				int year = calendar.get(Calendar.YEAR);
				list.add(String.valueOf(year));
			}
		}
		else {
			// 往后
			for (int i = 0; i < xxDay; i++) {
				calendar.setTime(date);
				calendar.add(Calendar.YEAR, i);
				int year = calendar.get(Calendar.YEAR);
				list.add(String.valueOf(year));
			}

		}

		return list;
	}

	/**
	 * 获取向前X年和向后X年
	 * 
	 * @param date
	 * @param xYer
	 * @return
	 */
	public static String[] getFontXYearBackXYear(Date date, int xYer) {
		Set<String> set = new HashSet<String>();
		// 向后读
		List<String> tempList = new ArrayList<String>();
		tempList.addAll(getXXYears(new Date(), xYer));
		tempList.addAll(getXXYears(new Date(), -xYer));
		int[] arr = new int[tempList.size()];
		for (int i = 0, size = tempList.size(); i < size; i++) {
			set.add(tempList.get(i));
		}
		int x = 0;
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			arr[x] = Integer.parseInt(it.next());
			x++;
		}
		// 冒泡
		for (int i = 0; i < arr.length - 1; i++) {// 外层循环控制排序趟数
			for (int j = 0; j < arr.length - 1 - i; j++) {// 内层循环控制每一趟排序多少次
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}

		}
		String[] res = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				res[i] = String.valueOf(arr[i]);
			}
		}
		return res;
	}

	/**
	 * 获取当前年份
	 * 
	 * @param date 指定日期
	 * @param xxDay 今天 0 上x天 -x 下x天 x
	 * @return
	 */
	public static String getNowYear(Date date, int xxDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, xxDay);
		int year = calendar.get(Calendar.YEAR);
		return "" + year;
	}

	/**
	 * 获取当前月份
	 * 
	 * @param date 指定日期
	 * @param xxDay 今天 0 上x天 -x 下x天 x
	 * @return
	 */
	public static int getNowMonth(Date date, int xxDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, xxDay);
		int month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 从文件名中截取扩展名
	 * 
	 * @param fileName 文件名（含扩展名）
	 * @return
	 */
	public static String getFileExt(String fileName) {
		if (null == fileName)
			return "";

		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 获取用户真实ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 匹配字符为整数 浮点数
	 * 
	 * @param param
	 * @return
	 */
	public static boolean regexDoubleStr(String param) {
		// String regex =
		// "([0-9]\\d*\\.?\\d*)|(0\\.\\d*[0-9])|(-\\d.[0-9])|(-\\d*[1-9])|(-0\\.\\d*[0-9])";
		// Pattern pattern = Pattern.compile(regex);
		// Matcher matcher = pattern.matcher(param);
		boolean flag = false;
		if (null != param) {
			String tempVl = param.replace(".", "").replace("-", "");
			if (null != StringUtils.trimToNull(tempVl)) {
				flag = StringUtils.isNumeric(tempVl);
			}
		}
		return flag;
	}

	/**
	 * set转String[]并排序
	 * 
	 * @param s
	 * @return
	 */
	public static String[] setToArray(Set<String> s) {
		String[] arr = new String[s.size()];
		// Set-->数组
		s.toArray(arr);
		Arrays.sort(arr);
		return arr;
	}

	/**
	 * double保留两位小数
	 * 
	 * @param d
	 * @return
	 */
	public static double twoDecimalPlaces(double d) {
		DecimalFormat df = new DecimalFormat("######0.00");
		return Double.parseDouble(df.format(d));
	}

	/**
	 * double保留两位小数,不四舍五入
	 * 
	 * @param d
	 * @return
	 */
	// public static double twoDecimalPass(double d) {
	// DecimalFormat df = new DecimalFormat("######0.00");
	// df.setRoundingMode(RoundingMode.FLOOR);
	// return Double.parseDouble(df.format(d));
	// }
	/**
	 * 图片转为 base64
	 * 
	 * @param imgFile
	 * @return
	 */
	public static String image2Base64(String imgFile) {
		String base64Image = null;
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
			// 加密
			Base64 base64 = new Base64();
			base64Image = new String(base64.encode(data));
			// log.info("图片转为Base64转义 成功 >>> ");
		}
		catch (IOException e) {
			// log.error("图片转为Base64 异常 >>> ");
		}
		finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				}
				catch (IOException e) {
				}
			}
		}
		return base64Image;
	}

	/**
	 * 将base64字符解码保存
	 * 
	 * @param base64Code
	 * @param savePath
	 * @return
	 * @throws IOException
	 */
	public static String writeImgToFile(String base64Code, String savePath) {
		base64Code = base64Code.replace(" ", "+");
		String fileName = UUID.randomUUID().toString().concat(".jpg");
		OutputStream out = null;
		Base64 base64 = new Base64();
		try {
			File file = new File(savePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(savePath.concat(fileName));
			if (!file.isFile()) {
				file.createNewFile();
			}
			// 解密
			byte[] b = base64.decode(base64Code.getBytes());
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			out = new FileOutputStream(savePath.concat("/").concat(fileName));
			out.write(b);
			out.flush();
			out.close();
		}
		catch (Exception e) {
		}
		finally {
			if (null != out) {
				try {
					out.flush();
					out.close();
				}
				catch (IOException e) {
				}

			}
		}
		return savePath.substring(savePath.indexOf("upload/"), savePath.length()).concat(fileName).replace("upload/", "");
	}

	/**
	 * @Description: 将base64编码字符串转换为图片
	 * @Author:
	 * @CreateTime:
	 * @param imgStr base64编码字符串
	 * @param path 图片路径-具体到文件
	 * @return
	 */
	public static boolean base642image(String imgStr, String path) {
		imgStr = imgStr.replace(" ", "");
		boolean flag = false;
		if (imgStr == null) {
			flag = false;
		}
		OutputStream out = null;
		Base64 base64 = new Base64();
		try {
			// 解密
			byte[] b = base64.decode(imgStr.getBytes());
			// 处理数据
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			flag = true;
		}
		catch (Exception e) {
			flag = false;
		}
		finally {
			if (null != out) {
				try {
					out.flush();
					out.close();
				}
				catch (IOException e) {
				}

			}
		}
		return flag;
	}

	/**
	 * 获取相应日历日期
	 * 
	 * @param date 指定日期
	 * @param xxDay 今天 0 上x天 -x 下x天 x
	 * @return
	 */
	public static Date getXXDay(Date date, int xxDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, xxDay);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 得到前n天的日期 yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getAddDayBefore(Date date, int toda) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -toda);
		return sf.format(c.getTime());
		
		
	}

	/**
	 * 可直接指定压缩后的宽高： (先保存原文件，再压缩、上传) 壹拍项目中用于二维码压缩
	 * 
	 * @param oldFile 要进行压缩的文件全路径
	 * @param width 压缩后的宽度（传入的参数大于0时，压缩后的宽度为传入的宽度）
	 * @param height 压缩后的高度
	 * @param quality 压缩质量
	 * @param smallIcon 文件名的小小后缀(注意，非文件后缀名称),入压缩文件名是yasuo.jpg,则压缩后文件名是yasuo(+smallIcon).jpg
	 * @return 返回压缩后的文件的全路径
	 */
	public static String zipImageFile(String oldFile, int width, int height, float quality, String smallIcon) {
		if (oldFile == null) {
			return null;
		}
		String newImage = null;
		FileOutputStream out = null;
		try {
			/** 对服务器上的临时文件进行处理 */
			BufferedImage srcFile = ImageIO.read(new File(oldFile));
			int width1 = srcFile.getWidth();
			int height1 = srcFile.getHeight();
			if (width > 0 && height > 0) {
				width1 = width;
				height1 = height;
			}
			/** 宽,高设定 */
			BufferedImage tag = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(srcFile, 0, 0, width1, height1, null);
			String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
			/** 压缩后的文件名 */
			newImage = filePrex + smallIcon + oldFile.substring(filePrex.length());
			/** 压缩之后临时存放位置 */
			out = new FileOutputStream(newImage);
			// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			// JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
			// /** 压缩质量 */
			// jep.setQuality(quality, true);
			// encoder.encode(tag, jep);
			ImageIO.write(tag, "jpg", out);
			out.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (null != out) {
				try {
					out.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return newImage;
	}

	/**
	 * 计算两个时间的秒
	 * 
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public static int calLastedTime(Date startDate, Date endDate) {
		long startTime = endDate.getTime();
		long endTime = startDate.getTime();
		int resTimeSecond = (int) ((startTime - endTime) / 1000);
		return resTimeSecond;
	}

	/**
	 * decoderBase64File:(将base64字符解码保存文件)
	 * 
	 * @param base64Code 编码后的字串
	 * @param savePath 文件保存路径
	 */
	public static String decoderBase64File(String base64Code, String savePath) {
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString().concat(".jpg");
		File file = new File(savePath.concat(File.separator));
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(savePath.concat(File.separator).concat(fileName));
		if (!file.exists()) {
			try {
				file.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileOutputStream out = null;
		try {
			Base64 base64 = new Base64();
			byte[] buffer = null;
			// 解密
			buffer = base64.decode(URLEncoder.encode(base64Code, "UTF-8").getBytes());
			out = new FileOutputStream(file);
			out.write(buffer);
			out.flush();
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (null != out) {
				try {
					out.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileName;
	}

	/**
	 * 利用json转换对象
	 * 
	 * @param <T>
	 * @param json
	 * @param classType
	 * @return 对象
	 */
	public static <T> T gsonToObject(String json, Class<T> classType) {
		Gson gson = new Gson();
		T t = gson.fromJson(json, classType);
		return t;
	}

	/**
	 * 判断全是数字和字母
	 * 
	 * @param data
	 * @return
	 */
	public static boolean isNumberAndAZ(String data) {
		if (null == data) {
			return false;
		}
		return data.matches("^[0-9a-zA-Z]+$");
	}

	/**
	 * 判断全是数字
	 * 
	 * @param data
	 * @return
	 */
	public static boolean isNumber(String data) {
		if (null == data) {
			return false;
		}
		return data.matches("^[0-9]+$");
	}

	/**
	 * 
	 * @param filePath 文件位置
	 * @param content 写入消息
	 * @return 0 -- 初始异常 1 -- 成功 2 -- 文件找不到 3 -- utf-8编码异常 4 -- 写入数据异常
	 */
	public static int writeContent2File(String filePath, String fileName, String content) {
		int isSuccess = 0;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(filePath.concat(fileName));
		FileOutputStream fos = null;
		if (!file.isFile()) {
			try {
				file.createNewFile();
			}
			catch (IOException e) {
			}
		}
		if (file.isFile()) {
			try {
				fos = new FileOutputStream(file);
				isSuccess = 1;
				fos.write(content.getBytes("utf-8"));
				fos.flush();
				fos.close();
			}
			catch (FileNotFoundException e) {
				isSuccess = 2;
			}
			catch (UnsupportedEncodingException e) {
				isSuccess = 3;
			}
			catch (IOException e) {
				isSuccess = 4;
			}
			finally {
				if (null != fos) {
					try {
						fos.flush();
						fos.close();
					}
					catch (IOException e) {
					}
				}
			}
		}
		else {
			isSuccess = 2;
		}
		return isSuccess;
	}

	/**
	 * 获取客户端ip
	 * 
	 * @param request
	 * @return 客户端的IP地址
	 */
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 克转换为斤 1斤=500克
	 * 
	 * @return
	 */
	public static int gToJin(int netWeight) {
		int jin = (int) (netWeight / (500 * 1.0));
		// return fmatDouble2(jin);
		return jin;
	}
	
	/**
	 * 去除字符串中的单引号'
	 * 
	 * @param str
	 * @return
	 */
	public static  String removeSingleQuote(String str) {
		String result = str;
		if(null != str) {
			result = str.replaceAll("\\'", "");
		}
		return result;
	}
	
	/**
	 * 随机数(抽奖中)
	 * @param num
	 * @return
	 */
	public static int draw(int num) {
		Random r = new Random();
		int a = 1 + r.nextInt(num);
		return a;
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		int a;
		a = 1 + r.nextInt(5);
		System.out.println(a);
		
	}
	
	/**
	 * base64编码
	 * 
	 * @param source
	 * @return
	 */
	public static String base64Encode(String source) {
		try {
			source = Base64.encodeBase64String(source.getBytes("utf-8"));
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return source;
	}

	/**
	 * base64解码
	 * 
	 * @param source
	 * @return
	 */
	public static String base64Decode(String source) {
		try {
			source = new String(Base64.decodeBase64(source), "utf-8");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return source;
	}
	
	/**
	 * 两个时间相差分钟数
	 * @param str1
	 * @param str2
	 * @return
	 * @throws ParseException 
	 */
	public static int getDistanceTimes(String s1, String s2) throws ParseException {  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one = sdf.parse(s1);
		Date two = sdf.parse(s2);
        long min = 0;  
        long time1 = one.getTime();  
        long time2 = two.getTime();  
        long diff ;  
        if(time1<time2) {  
            diff = time2 - time1;  
        } else {  
            diff = time1 - time2;  
        }  
        min = diff / (60 * 1000); 
        return (int)min;
    }
	
	/**
	 * easyui排序字段转数据库列名
	 * @param s
	 * @return
	 */
	public static String change(String s) {
		String original = s;
		String lowerCase = s.toLowerCase();
		String oldReplace;
		String newReplace;
		int length = s.length();
		for (int i = 0; i < length; i++) {
			if (original.charAt(i) != lowerCase.charAt(i)) {
				oldReplace = String.valueOf(original.charAt(i));
				newReplace = "_" + lowerCase.charAt(i);
				s = s.replace(oldReplace, newReplace);
			}
		}
		return s;

	}
}
