package com.gang.utils;

import com.google.gson.Gson;

/**
 * gson工具类
 * 
 * @author Yang xt
 * 
 * @Aug 11, 2017
 * 
 */
public class GsonUtil {

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
}
