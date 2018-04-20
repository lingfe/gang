package com.gang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gang.entity.ClothingInfo;
import com.gang.service.ClothingInfoService;

/**
 * 服装信息，请求访问层
 * @author lingfe
 * @date 2018.04.18
 *
 */
@Controller
@RequestMapping(value="clothingInfo")
public class ClothingInfoController {
	
	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(ClothingInfoController.class);

	@Autowired
	private ClothingInfoService clothingInfoService;
	
	/**
	 * 根据id得到服装信息
	 * @param id 服装信息id标识
	 * @author lingfe
	 * @return 数据
	 */
	@RequestMapping(value = "/getClothingInfoWhereId", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ClothingInfo>  getClothingInfoWhereId(String id) {
		//得到数据
		List<ClothingInfo> st=clothingInfoService.getClothingInfoWhereId(id);
		log.info("得到数据!");
		return st;
	}
	
	/**
	 * 根据服装款式id得到服装信息
	 * @param styletypeinfoId	服装款式id主键
	 * @param styleName			服装款式名称
	 * @author lingfe
	 * @return 数据
	 */
	@RequestMapping(value = "/getClothingInfoList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ClothingInfo>  getClothingInfoList(String styletypeinfoId,String styleName) {
		//得到数据
		List<ClothingInfo> st=clothingInfoService.getClothingInfoList(styletypeinfoId, styleName);
		log.info("得到数据!");
		return st;
	}
}
