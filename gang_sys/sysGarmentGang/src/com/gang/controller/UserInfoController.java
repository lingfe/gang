package com.gang.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gang.entity.UserInfo;
import com.gang.service.UserInfoService;
import com.gang.utils.EggsUserInfoSessionKey;

/**
 * 用户操作，请求访问层
 * @author lingfe
 * @date 2018.04.18
 *
 */
@Controller
@RequestMapping(value="user")
public class UserInfoController {
	
	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * pc 进入登陆
	 * @param session	会话
	 * @param request	请求
	 * @author lij
	 * @return
	 */
	@RequestMapping("init")
	public ModelAndView init(HttpSession session, HttpServletRequest request,String return_url) {
		ModelAndView mav = new ModelAndView("houtai/login");
		log.info("进入登陆!...");
		mav.addObject("return_url", return_url);
		return mav;
	}
	
	
	/**
	 * 请求登陆后台
	 * @param userName
	 * @param pwd
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login(String userName,String pwd,
			HttpSession session, HttpServletRequest request){
		ModelAndView mav=new ModelAndView("houtai/login");
		//验证非空
		if(userName==null||"".equals(userName)){
			mav.addObject("msg", "请输入用户名");
			log.info("用户名为空!");
		}else if(pwd==null||"".equals(pwd)){
			mav.addObject("msg", "请输入密码");
			log.info("密码为空!");
		}else{
			//执行查询
			UserInfo userInfo=userInfoService.loginSelect(userName, pwd);
			//验证
			if(userInfo!=null){
				log.info("账号正确，验证成功!...");
				//存到session
				session.setAttribute(EggsUserInfoSessionKey.WSC_USERINFO_SESSION, userInfo);
				mav.addObject("loginDate", new Date());
				//修改登陆时间
				userInfoService.updateLastTime(new Date(), userInfo.getId());
				mav.setViewName("houtai/mian");
			}else{
				log.error("登陆失败!用户名或密码错误!...");
				mav.addObject("msg", "登陆失败!用户名或密码错误!");
			}	
		}
		return mav;
	}
}
