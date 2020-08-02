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
import com.gang.entity.Appointmentinformation;
import com.gang.entity.ClothingInfo;
import com.gang.entity.StyleTypeInfo;
import com.gang.entity.SysLog;
import com.gang.entity.UserInfo;
import com.gang.pojo.SelectWhere;
import com.gang.service.AppointmentinformationService;
import com.gang.service.ClothingInfoService;
import com.gang.service.LogService;
import com.gang.service.StyleTypeInfoService;
import com.gang.service.UserInfoService;
import com.gang.service.utils.GetIpUtil;
import com.gang.utils.OpenIdUtil;

/**
 * 后台管理，操作请求
 * @author 13068 lingfe
 * @date 2018.04.25
 *
 */
@Controller
@RequestMapping("manage")
public class MianManageController {

	/**
	 * 日志
	 */
	private static Logger log = LoggerFactory.getLogger(MianManageController.class);
	@Autowired
	private LogService logService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private StyleTypeInfoService styleTypeInfoService;
	
	@Autowired
	private ClothingInfoService clothingInfoService;
	
	@Autowired
	private AppointmentinformationService appointmentinformationService;

	
	@RequestMapping(value="/openid",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String getOpenid(String code){
		String oepnid=OpenIdUtil.oauth2GetOpenid(code, "1");
		return oepnid;
	}
	
	/**
	 * 进入登陆
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("init")
	public ModelAndView init(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("houtai/login");
		return mav;
	}
	
	@RequestMapping(value="/manage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView manage(HttpSession session, HttpServletRequest request){
		ModelAndView mav=new ModelAndView("houtai/mian");
		mav.addObject("正在开发中...");
		return mav;
	}
	
	/**
	 * 访问日志管理
	 * @param page	当前页
	 * @param rows	页容量
	 * @param session	会话
	 * @param request	请求
	 * @author 13068	lingfe
	 * @return 数据
	 */
	@RequestMapping(value="/accessLogManage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView accessLogManage(
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="rows",defaultValue="10") int rows, 
			SelectWhere where,
			HttpSession session, HttpServletRequest request) {
		//系统日志记录
		SysLog sysLog=new SysLog();
		sysLog.setCreator("管理员");
		sysLog.setModel("/accessLogManage");
		sysLog.setUserName("管理员");
		try {
			ModelAndView mav=new ModelAndView("houtai/accessLogManage");
			//获取数据
			List<AccessLog> accessLogList=logService.getAccessLogList(where,page, rows);
			mav.addObject("accessLogList", accessLogList);
			if(accessLogList.size()!=0||accessLogList!=null){
				//得到总数据量
			    int total=logService.getAccessLogList(new SelectWhere(),null, null).size();
			    
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
			sysLog.setOperationType("查询");
			
			return mav;
		} catch (Exception e) {
			sysLog.setAbnormal(e.getMessage());
			e.printStackTrace();
		}finally {
			logService.addSysLog(sysLog);
		}
		
		return null;
	}
	
	/**
	 * 系统日志管理
	 * @param page	当前页
	 * @param rows	页容量
	 * @param session	会话
	 * @param request	请求
	 * @author 13068	lingfe
	 * @return 数据
	 */
	@RequestMapping(value="/sysLogManage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView sysLogManage(
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="rows",defaultValue="10") int rows, 
			SelectWhere where,
			HttpSession session, HttpServletRequest request) {
		try {
			ModelAndView mav=new ModelAndView("houtai/sysLogManage");
			//获取数据
			List<SysLog> sysLogList=logService.getSysLogList(where,page, rows);
			mav.addObject("sysLogList", sysLogList);
			if(sysLogList.size()!=0||sysLogList!=null){
				//得到总数据量
			    int total=logService.getSysLogList(new SelectWhere(),null, null).size();
			    
				//返回相关分页参数
				Map<String, Object> pageInfo = new HashMap<String, Object>();
				pageInfo.put("pageSize", rows); 
				pageInfo.put("pageNum", page); 
				pageInfo.put("total", total); 
				mav.addObject("pageInfo", pageInfo);
			}
			
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 预约管理
	 * @param page	当前页
	 * @param rows	页容量
	 * @param session	会话
	 * @param request	请求
	 * @author 13068	lingfe
	 * @return 数据
	 */
	@RequestMapping(value="/appointManage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView appointManage(
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="rows",defaultValue="10") int rows, 
			SelectWhere where,
			Appointmentinformation appoint,
			HttpSession session, HttpServletRequest request) {
		
		//系统日志记录
		SysLog sysLog=new SysLog();
		sysLog.setCreator("管理员");
		sysLog.setModel("/appointManage");
		sysLog.setUserName("管理员");
		sysLog.setOperationType("查询");
		try {
			ModelAndView mav=new ModelAndView("houtai/appointManage");
			//判断要操作的类型
			if(where.getParam() !=null &&!"".equals(where.getParam())){
				if(where.getParam().equals("delete")){
					//执行删除
					sysLog.setOperationType("删除");
					appointmentinformationService.deleteWhereId(appoint.getId());
				}else if(where.getParam().equals("update")){
					//修改
					//先删除原来的数据，保留id
					int tt=appointmentinformationService.deleteWhereId(appoint.getId());
					if(tt>=1){
						//然后，再重新添加以达到修改的修改
						//赋值
						appoint.setMdate(new Date());
						Integer version=Integer.parseInt(appoint.getVersion())+1;
						appoint.setVersion(version.toString());
						sysLog.setOperationType("修改");
						appointmentinformationService.add(appoint);
					}
				}
			}
			//获取数据
			List<Appointmentinformation> appointList=appointmentinformationService.getInfoList(where,page, rows);
			mav.addObject("appointList", appointList);
			if(appointList.size()!=0||appointList!=null){
				//得到总数据量
			    int total=appointmentinformationService.getInfoList(new SelectWhere(),null, null).size();
			    
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
			
			return mav;
		} catch (Exception e) {
			sysLog.setAbnormal(e.getMessage());
			e.printStackTrace();
		}finally {
			logService.addSysLog(sysLog);
		}
		
		return null;
	}
	
	/**
	 * 服装信息管理
	 * @param page	当前页
	 * @param rows	页容量
	 * @param session	会话
	 * @param request	请求
	 * @author 13068	lingfe
	 * @return 数据
	 */
	@RequestMapping(value="/clothingInfoManage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView clothingInfoManage(
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="rows",defaultValue="10") int rows,
			SelectWhere where,
			ClothingInfo clo,
			HttpSession session,HttpServletRequest request){
		
		//系统日志记录
		SysLog sysLog=new SysLog();
		sysLog.setCreator("管理员");
		sysLog.setModel("/clothingInfoManage");
		sysLog.setUserName("管理员");
		sysLog.setOperationType("查询");
		try {
			ModelAndView mav=new ModelAndView("houtai/clothingInfoManage");
			where.setPageIndex(page);
			where.setPageNum(rows);
			//验证要操作的类型
			if(where.getParam()!=null&&!"".equals(where.getParam())){
				if(where.getParam().equals("delete")){
					//执行删除
					sysLog.setOperationType("delete");
					clothingInfoService.deleteWhereId(clo.getId());
				}else if(where.getParam().equals("update")){
					//执行修改
					sysLog.setOperationType("update");
					//先删除原来的数据，保留id
					int tt=clothingInfoService.deleteWhereId(clo.getId());
					if(tt>=1){
						//再执行添加，以达到修改的效果
						//赋值
						clo.setMdate(new Date());
						Integer version=Integer.parseInt(clo.getVersion())+1;
						clo.setVersion(version.toString());
						
						clothingInfoService.addClothingInfo(clo);
					}
				}else if(where.getParam().equals("isDisplay")){
					//执行修改为隐藏或显示
					sysLog.setOperationType("isDisplay");
					clothingInfoService.updateIsDisplay(clo.getId(), clo.getIsDisplay());
				}else if(where.getParam().equals("add")){
					//执行添加
					sysLog.setOperationType("add");
					//赋值
					clo.setCdate(new Date());
					clo.setState(0);
					clo.setCreator("admin");
					clo.setMaterial("admin");
					clo.setId(UUID.randomUUID().toString());
					
					clo.setMdate(new Date());
					clo.setVersion("0");
					clothingInfoService.addClothingInfo(clo);
				}
			}
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
			
			return mav;
		} catch (Exception e) {
			sysLog.setAbnormal(e.getMessage());
			e.printStackTrace();
		}finally {
			logService.addSysLog(sysLog);
		}
		
		return null;
	}
	
	/**
	 * 服装款式类型信息管理
	 * @param page	当前页
	 * @param rows	页容量
	 * @param session	会话
	 * @param request	请求
	 * @author 13068	lingfe
	 * @return	数据
	 */
	@RequestMapping(value="/styleTypeManage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView styleTypeManage(
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="rows",defaultValue="10") int rows,
			StyleTypeInfo style,
			SelectWhere where,
			HttpSession session,HttpServletRequest request){
		//系统日志记录
		SysLog sysLog=new SysLog();
		sysLog.setCreator("管理员");
		sysLog.setModel("/styleTypeManage");
		sysLog.setUserName("管理员");
		sysLog.setOperationType("查询");
		
		try {
			ModelAndView mav=new ModelAndView("houtai/styleTypeManage");
			where.setPageIndex(page);
			where.setPageNum(rows);
			//判断要执行的操作
			if(where.getParam() !=null &&!"".equals(where.getParam())){
				if(where.getParam().equals("delete")){
					//执行删除
					sysLog.setOperationType("删除");
					styleTypeInfoService.deleteWhereId(style.getId());
				}else if(where.getParam().equals("update")){
					//执行修改
					sysLog.setOperationType("修改");
					style.setMdate(new Date());
					Integer version=Integer.parseInt(style.getVersion())+1;
					style.setVersion(version.toString());
					//先执行删除
					int tt=styleTypeInfoService.deleteWhereId(style.getId());
					if(tt>=1){
						//然后重新添加达到修改的效果
						styleTypeInfoService.addStyleInfo(style);
					}
					
				}else if(where.getParam().equals("isDisplay")){
					//执行隐藏或显示
					sysLog.setOperationType("隐藏或显示");
					styleTypeInfoService.updateIsDisplay(style.getId(), style.getIsDisplay());
				}else if(where.getParam().equals("add")){
					//执行添加
					sysLog.setOperationType("添加");
					//赋值
					style.setId(UUID.randomUUID().toString());
					style.setCdate(new Date());
					style.setMdate(new Date());
					style.setVersion("0");
					
					styleTypeInfoService.addStyleInfo(style);
				}
			}
			
			//获取数据
			List<StyleTypeInfo> styleTypeInfoList=styleTypeInfoService.getInfoList(where);
			mav.addObject("styleTypeInfoList", styleTypeInfoList);
			if(styleTypeInfoList.size()!=0||styleTypeInfoList!=null){
				//得到总数据量
			    int total=styleTypeInfoService.getInfoList(new SelectWhere()).size();
			    
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
			
			
			return mav;
		} catch (Exception e) {
			sysLog.setAbnormal(e.getMessage());
			e.printStackTrace();
		}finally {
			logService.addSysLog(sysLog);
		}
		
		return null;
	}
	
	/**
	 * 用户管理
	 * @param page	当前页
	 * @param rows	页容量
	 * @param session 	会话
	 * @param request	请求
	 * @author 13068	lingfe
	 * @return 数据
	 */
	@RequestMapping(value="/userManage",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView userManage(
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="rows",defaultValue="10") int rows,
			SelectWhere where,
			HttpSession session, HttpServletRequest request){
		ModelAndView mav=new ModelAndView("houtai/userManage");
		where.setPageIndex(page);
		where.setPageNum(rows);
		//得到数据
	    List<UserInfo> userInfoList= userInfoService.getUserInfo(where);
	    log.info("得到用户数据!..");
	    mav.addObject("userInfoList", userInfoList);
	    if(userInfoList.size()!=0||userInfoList!=null){
			//得到总数据量
		    int total=userInfoService.getUserInfo(null).size();
		    
			//返回相关分页参数
			Map<String, Object> pageInfo = new HashMap<String, Object>();
			pageInfo.put("pageSize", rows); 
			pageInfo.put("pageNum", page); 
			pageInfo.put("total", total); 
			mav.addObject("pageInfo", pageInfo);
		}
		return mav;
	}
	
}
