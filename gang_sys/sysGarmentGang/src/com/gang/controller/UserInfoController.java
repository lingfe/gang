package com.gang.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gang.entity.UserInfo;
import com.gang.service.UserInfoService;
import com.gang.service.utils.GetIpUtil;
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
	 * 移动端用户注册
	 * @param user		用户注册信息
	 * @param session	会话
	 * @param request	请求
	 * @author 13068	lingfe
	 * @return 
	 */
	@RequestMapping(value="/registerApp",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public UserInfo registerApp(UserInfo user,
			HttpSession session, HttpServletRequest request){
		
		//执行查询
		UserInfo userInfo=userInfoService.loginSelect(user.getUserName(), user.getPwd());
		//验证
		if(userInfo!=null){
			userInfo.msg="该用户名已经被注册!";
			userInfo.status=1;
			return userInfo;
		}
		
		//赋值
		user.setId(UUID.randomUUID().toString());
		user.setCdate(new Date());
		user.setMdate(user.getCdate());
		user.setVersion("0");
		user.setCreator(user.getUserName());
		user.setState(0);
		user.setModify(user.getUserName());
		user.setLastTime(new Date());
		user.setAppId(GetIpUtil.getIpAddr(request));
		
		//执行注册
		int tt=userInfoService.add(user);
		if(tt>=1){
			user.msg="注册成功!";
			user.status=0;
			return user;
		}else{
			user.msg="注册失败!";
			user.status=1;
			return user;
		}
	}
	
	/**
	 * 移动端用户请求登陆
	 * @param userName	用户名
	 * @param pwd		密码
	 * @param session	会话
	 * @param request	请求
	 * @author 13068	lingfe
	 * @return
	 */
	@RequestMapping(value="/loginApp",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public UserInfo loginApp(String userName,String pwd,
			HttpSession session, HttpServletRequest request){
		UserInfo user=new UserInfo();
		//验证非空
		if(userName==null||"".equals(userName)){
			user.msg="请输入用户名";
			log.info("用户名为空!");
		}else if(pwd==null||"".equals(pwd)){
			user.msg="请输入密码";
			log.info("密码为空!");
		}else{
			//执行查询
			user=userInfoService.loginSelect(userName, pwd);
			//验证
			if(user!=null){
				log.info("账号正确，验证成功!...");
				//存到session
				session.setAttribute(EggsUserInfoSessionKey.WSC_USERINFO_SESSION, user);
				//修改登陆时间
				userInfoService.updateLastTime(new Date(), user.getId());
				user.msg="登陆成功!";
				user.status=0;
				return user;
			}else{
				log.error("登陆失败!用户名或密码错误!...");
				user.msg="登陆失败!用户名或密码错误!";
			}	
		}
		user.status=1;
		return user;
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
