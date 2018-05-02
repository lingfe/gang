package com.gang.utils;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 上下文环境
 * 
 * @author Yang xt
 * 
 * @May 8, 2017
 * 
 */
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext contex) throws BeansException {
		SpringContextUtil.context = contex;
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	public static String getMessage(String key) {
		return context.getMessage(key, null, Locale.getDefault());
	}

}
