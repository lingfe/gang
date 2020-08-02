package com.gang.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gang.entity.AccessLog;
import com.gang.entity.ClothingInfo;
import com.gang.entity.SysLog;
import com.gang.pojo.SelectWhere;
import com.gang.service.ClothingInfoService;
import com.gang.service.LogService;
import com.gang.service.utils.GetIpUtil;

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
	private LogService logService;
	
	@Autowired
	private ClothingInfoService clothingInfoService;
	
	/**
	 * 服装信息管理
	 * @param page	当前页
	 * @param rows	页容量
	 * @param session	会话
	 * @param request	请求
	 * @author 13068	lingfe
	 * @return 数据
	 */
	@RequestMapping(value="/getClothingInfoAll",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<ClothingInfo> getClothingInfoAll(
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="rows",defaultValue="10") int rows,
			SelectWhere where,
			HttpSession session,HttpServletRequest request){
		
		//系统日志记录
		SysLog sysLog=new SysLog();
		sysLog.setCreator("游客");
		sysLog.setModel("/clothingInfoManage");
		sysLog.setUserName("游客");
		sysLog.setOperationType("查询");
		try {
			ModelAndView mav=new ModelAndView("houtai/clothingInfoManage");
			where.setPageIndex(page);
			where.setPageNum(rows);
			
			//获取数据
			List<ClothingInfo> clothingInfoList= clothingInfoService.getClothingInfoWhereId(where,null);
			mav.addObject("clothingInfoList", clothingInfoList);
			if(clothingInfoList.size()!=0||clothingInfoList!=null){
				//得到总数据量
			    int total=clothingInfoService.getClothingInfoWhereId(new SelectWhere(), null).size();
			    
				//返回相关分页参数
				Map<String, Object> pageInfo = new HashMap<String, Object>();
				pageInfo.put("pageSize", rows); 
				pageInfo.put("pageNum", page); 
				pageInfo.put("total", total); 
				mav.addObject("pageInfo", pageInfo);
			}
			//log
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setAbnormal("无");
			
			return clothingInfoList;
		} catch (Exception e) {
			sysLog.setAbnormal(e.getMessage());
			e.printStackTrace();
		}finally {
			logService.addSysLog(sysLog);
		}
		
		return null;
	}
	
	/**
	 * 根据id得到服装信息
	 * @param id 服装信息id标识
	 * @author lingfe
	 * @return 数据
	 */
	@RequestMapping(value = "/getClothingInfoWhereId", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<ClothingInfo>  getClothingInfoWhereId(String id,HttpServletRequest request) {
		//系统日志记录
		SysLog sysLog=new SysLog();
		sysLog.setCreator("游客");
		sysLog.setUserName("游客");
		sysLog.setModel("根据id得到服装信息");
		//得到数据
		List<ClothingInfo> st=null;
		try {
			st = clothingInfoService.getClothingInfoWhereId(new SelectWhere(),id);
			log.info("得到数据!");
			
			//log
			sysLog.setIp(GetIpUtil.getIpAddr(request));
			sysLog.setAbnormal("无");
			sysLog.setOperationType("查询");
			
		} catch (Exception e) {
			sysLog.setAbnormal(e.getMessage());
			e.printStackTrace();
		}finally {
			logService.addSysLog(sysLog);
		}
		
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
	public List<ClothingInfo>  getClothingInfoList(String styletypeinfoId,String styleName,HttpServletRequest request) {
		
		//访问日志
		AccessLog accessLog=new AccessLog();
		accessLog.setAccessModel("根据服装款式id得到服装信息");
		accessLog.setCreator("游客");
		
		try {
			//得到数据
			List<ClothingInfo> st=clothingInfoService.getClothingInfoList(styletypeinfoId, styleName);
			log.info("得到数据!");
			
			//log
			accessLog.setIp(GetIpUtil.getIpAddr(request));
			accessLog.setAccessOperationType("查询");
			accessLog.setAccessAbnormal("无");
			
			return st;
		} catch (Exception e) {
			accessLog.setAccessAbnormal(e.getMessage());
			e.printStackTrace();
		}finally {
			logService.addAccessLogInfo(accessLog);
		}
		
		return null;
	}
}
