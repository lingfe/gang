package com.gang.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gang.entity.Appointmentinformation;
import com.gang.entity.SysLog;
import com.gang.service.AppointmentinformationService;
import com.gang.service.LogService;
import com.gang.service.utils.GetIpUtil;

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
	private LogService logService;
	
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
	public Appointmentinformation add(Appointmentinformation info,HttpServletRequest request){
		//系统日志记录
		SysLog sysLog=new SysLog();
		sysLog.setCreator("用户");
		sysLog.setModel("提交上门预约订单");
		sysLog.setUserName("游客");
		try {
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
			
			//log
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setAbnormal("无");
			sysLog.setOperationType("添加");
			
		} catch (Exception e) {
			sysLog.setAbnormal(e.getMessage());
			e.printStackTrace();
		}finally {
			logService.addSysLog(sysLog);
		}
		
		return info;
	}
}
