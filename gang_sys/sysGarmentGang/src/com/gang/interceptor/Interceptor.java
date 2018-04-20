package com.gang.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据拦截处理
 * 
 * @author yxt
 * @date Jan 13, 2017
 * 
 */
public class Interceptor implements HandlerInterceptor {

	// 还没发现可以直接配置不拦截的资源，所以在代码里面来排除
	public String[] allowUrls;

	// 必须使用set方法
	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		if (null != allowUrls && allowUrls.length >= 1) {
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}
		}
		HttpSession session = request.getSession();
		if (null != session) {
			// 逻辑处理
			return true;
		}
		else {
			String method = request.getMethod();
			if ("POST".equals(method.toUpperCase())) {
				PrintWriter out = response.getWriter();
				StringBuilder buffer = new StringBuilder();
				buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				buffer.append("<message><msg>ERROR</msg><msgContent>ILLEGAL REQUEST</msgContent></message>");
				out.write(buffer.toString());
				out.flush();
				out.close();
			}
			else {
				// 进入登录
				response.sendRedirect(request.getContextPath());
			}

		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest reques, HttpServletResponse rsponse, Object arg2, Exception arg3) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

}
