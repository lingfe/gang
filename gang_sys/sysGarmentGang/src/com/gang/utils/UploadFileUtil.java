package com.gang.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 文件上传
 * 
 * @author YangXiuTeng
 * 
 * @date Jun 1, 2015
 */
public class UploadFileUtil {

	/**
	 * 文件上传 注意：上传文件路径定义为 去除 upload下第一级目录 如: /xx/xxx.pdf
	 * 
	 * @param request 上传文件 request
	 * @param path 文件存储位置
	 * @return 上传文件名称
	 */
	public static List<Map<String, String>> uploadFile(HttpServletRequest request, String path) {
		List<Map<String, String>> fileNameList = new ArrayList<Map<String, String>>();
		// 文件名与路劲匹配
		Map<String, String> tempMap = null;
		// 上传目标路径
		String basePath = request.getSession().getServletContext().getRealPath("upload");
		// 得到文件的路径
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 只读file
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取文件原名字
			Iterator<String> iter = multiRequest.getFileNames();
			String fileName = "";
			StringBuffer uploadFileInfo = new StringBuffer();
			File local = null;
			MultipartFile file = null;
			while (iter.hasNext()) {
				// 得到上传文件
				file = multiRequest.getFile(iter.next());
				if (file != null) {
					// file.getOriginalFilename() 获取上传文件名
					String prex = file.getOriginalFilename();
					if (null != StringUtils.trimToNull(prex)) {
						uploadFileInfo.setLength(0);
						// 创建文件
						tempMap = new HashMap<String, String>();
						prex = ".".concat(prex.substring(prex.lastIndexOf(".") + 1));
						// 规范上传文件位置、文件名
						fileName = UUID.randomUUID().toString().concat(prex);
						uploadFileInfo.append(path).append("/").append(fileName);
						// 装入MAP
						tempMap.put("fileName", file.getOriginalFilename());
						tempMap.put("fileUrl", fileName.replaceAll("\\\\", "/"));
						fileNameList.add(tempMap);
						local = new File(basePath.concat(uploadFileInfo.toString()));
						// 判断路径是否存在，不存在则创建路径
						if (!local.exists()) {
							local.mkdirs();
						}
						try {
							// 传到服务器指定位置
							file.transferTo(local);
						}
						catch (IllegalStateException e) {
							// e.printStackTrace();
						}
						catch (IOException e) {
							// e.printStackTrace();
						}
					}

				}
			}
		}
		return fileNameList;
	}

	/**
	 * 多文件上传
	 * 
	 * @param files
	 * @param request
	 * @param path
	 * @return
	 */
	public static List<Map<String, String>> uploadMultipartFile(MultipartFile[] files, HttpServletRequest request, String path) {
		List<Map<String, String>> fileNameList = new ArrayList<Map<String, String>>();
		// 文件名与路径匹配
		Map<String, String> tempMap = null;
		// 上传目标路径
		String basePath = request.getSession().getServletContext().getRealPath("upload");
		// 判断files数组不能为空并且长度大于0
		if (null != files && files.length > 0) {
			String fileName = "";
			StringBuffer uploadFileInfo = new StringBuffer();
			File local = null;
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 保存文件
				if (!file.isEmpty()) {
					// file.getOriginalFilename() 获取上传文件名
					String prex = file.getOriginalFilename();
					if (null != StringUtils.trimToNull(prex)) {
						uploadFileInfo.setLength(0);
						// 创建文件
						tempMap = new HashMap<String, String>();
						prex = ".".concat(prex.substring(prex.lastIndexOf(".") + 1));
						// 规范上传文件位置、文件名
						fileName = UUID.randomUUID().toString().concat(prex);
						uploadFileInfo.append(path).append("/").append(fileName);
						// 装入MAP
						tempMap.put("fileName", file.getOriginalFilename());
						tempMap.put("fileUrl", path.concat("/").concat(fileName.replaceAll("\\\\", "/")));
						fileNameList.add(tempMap);
						local = new File(basePath.concat(uploadFileInfo.toString()));
						// 判断路径是否存在，不存在则创建路径
						if (!local.exists()) {
							local.mkdirs();
						}
						try {
							// 传到服务器指定位置
							file.transferTo(local);
						}
						catch (IllegalStateException e) {
							// e.printStackTrace();
						}
						catch (IOException e) {
							// e.printStackTrace();
						}
					}
				}
			}
		}
		return fileNameList;
	}
	
	/**
     * 删除文件
     * @param fileName
     * @param path
     */
    public static void delFile(String fileName, String path){
        File file  = new File(path + fileName);
        try {
            if(file.exists()){
                file.delete();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
