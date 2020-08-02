package com.gang.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gang.entity.UserInfo;
import com.gang.utils.EggsUserInfoSessionKey;

/**
 * 拦截器
 * 
 * @author Yang xt
 * 
 * @May 12, 2017
 * 
 */
public class AutFilter implements Filter {

	/**
	 * @auth dongk 2018-3-2 15:03:47
	 * @description 开发阶段,以下部分页面权限为测试需要,后期需统一管理页面权限
	 */
	public static String[] allowUrls = {"user/loginApp","user/registerApp","manage/openid","user/login.action","user/init","appint","styleTypeInfo/getInfoList","getInfoList","clothingInfo"};
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		boolean flag = false;
		String uri = request.getRequestURI().replaceAll(request.getContextPath(), "");
		if (null != allowUrls && allowUrls.length > 0) {
			for (String url : allowUrls) {
				if (uri.contains(url)) {
					flag = true;
					break;
				}
			}
		}
		// 执行链接不在拦截中
		if (false == flag) {
			if (null == session) {
				// 执行重新登录
				response.sendRedirect(request.getContextPath());
			}
			else {
				UserInfo userInfo = (UserInfo) session.getAttribute(EggsUserInfoSessionKey.WSC_USERINFO_SESSION);
				if (null == userInfo) {
					RequestDispatcher rd = null;
					if(uri.contains("user/login.action")){
						rd = request.getRequestDispatcher("/user/init.action");
					}else{
					    rd = request.getRequestDispatcher("/user/init.action");//后台登陆
					}
					rd.forward(request, response);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
