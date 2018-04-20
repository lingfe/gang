package com.gang.controller;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gang.entity.Appointmentinformation;
import com.gang.service.AppointmentinformationService;

/**
 * 上门预约，请求访问层
 * @author lingfe
 * @date 2018.04.18
 *
 */
@Controller
@RequestMapping(value="appint")
public class AppointmentinformationController {
	
	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(AppointmentinformationController.class);
	
	@Autowired
	private AppointmentinformationService appointmentinformationService;
	
	/**
	 * 上门预约
	 * @param info	实体
	 * @author lingfe
	 * @return 结果
	 */
	@RequestMapping(value="/add",method={ RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Appointmentinformation add(Appointmentinformation info){
		//赋值
		info.setState(0);
		info.setCdate(new Date());
		info.setMdate(info.getCdate());
		info.setCreator("java");
		info.setId(UUID.randomUUID().toString());
		info.setModify("java");
		info.setVersion("0");
		
		//执行添加
		appointmentinformationService.add(info);
		log.info("添加一条上门预约信息");
		return info;
	}
}
