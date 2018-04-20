package com.gang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gang.entity.StyleTypeInfo;
import com.gang.service.StyleTypeInfoService;

/**
 * 服装款式信息，请求访问层
 * @author 13068
 * @date 2018.04.16
 */
@Controller
@RequestMapping(value="styleTypeInfo")
public class StyleTypeInfoController {

	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(StyleTypeInfoController.class);
	
	@Autowired 
	private StyleTypeInfoService styleTypeInfoService;
	

	/**
	 * 得到所有数据
	 * @param session	会话
	 * @param request	请求
	 * @return 数据
	 */
	@RequestMapping(value = "/getInfoList", method = RequestMethod.GET)
	@ResponseBody
	public List<StyleTypeInfo> getInfoList() {
		//得到数据
		List<StyleTypeInfo> st=styleTypeInfoService.getInfoList();
		log.info("得到数据!");
		return st;
	}
}
