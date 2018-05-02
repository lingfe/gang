package com.gang.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 办公文档处理工具类
 * 
 * @author yxt
 * @date Jan 22, 2017
 * 
 */
public class OfficeProcess {
	private static Logger log = LoggerFactory.getLogger(OfficeProcess.class);
	/**
	 * 根据模板生成Excel文件
	 * 
	 * 通用 list 作为标记
	 * 
	 * @param templateFileName 模板文件
	 * @param list 模板中存放的数据
	 * @param resultFileName 生成的文件
	 */
	public static boolean createExportExcel(String templateFileName, List<?> list, String resultFileName) {
		boolean flag = false;
		// 创建XLSTransformer对象
		XLSTransformer transformer = new XLSTransformer();
		// 得到模板文件路径
		String srcFilePath = templateFileName;
		Map<String, Object> beanParams = new HashMap<String, Object>();
		beanParams.put("list", list);
		String destFilePath = resultFileName;
		try {
			// 生成Excel文件
			transformer.transformXLS(srcFilePath, beanParams, destFilePath);
			flag = true;
		}
		catch (ParsePropertyException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 0 -- 未找到excel路径 1 -- 正常 2 -- 导入异常
	 * 
	 * @param excelFilePath
	 * @param imgPath 图像路径
	 * @param dx1
	 * @param dy1
	 * @param dx2
	 * @param dy2
	 * @param col1
	 * @param row1
	 * @param col2
	 * @param row2
	 * @return
	 */
	public static int writeImg2Excel(String excelFilePath, String imgPath, int dx1, int dy1, int dx2, int dy2, short col1, int row1, short col2, int row2) {
		File file = new File(excelFilePath);
		int flag = 2;
		if (file.isFile()) {
			FileOutputStream fileOut = null;
			BufferedImage bufferImg = null;
			HSSFWorkbook wb = null;
			// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
			try {
				file = new File(imgPath);
				if (file.isFile() && file.exists()) {
					ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
					bufferImg = ImageIO.read(file);
					ImageIO.write(bufferImg, "jpg", byteArrayOut);
					file = new File(excelFilePath);
					wb = new HSSFWorkbook(new FileInputStream(file));
					HSSFSheet sheet = wb.getSheetAt(0);
					// 画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
					HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
					// anchor主要用于设置图片的属性
					// HSSFClientAnchor(int dx1,int dy1,int dx2,int dy2,short col1,int row1,short col2,int row2);
					/**
					 * col1 图片的左上角放在第几个列cell， row1 图片的左上角放在第几个行cell，
					 * 
					 * col2 图片的右下角放在第几个列cell， row2 图片的右下角放在第几个行cell
					 */
					HSSFClientAnchor anchor = new HSSFClientAnchor(dx1, dy1, dx2, dy2, col1, row1, col2, row2);
					anchor.setAnchorType(3);
					// 插入图片
					patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), org.apache.poi.ss.usermodel.Workbook.PICTURE_TYPE_JPEG));
					fileOut = new FileOutputStream(excelFilePath);
					// 写入excel文件
					wb.write(fileOut);
					wb.close();
					bufferImg.flush();
					flag = 1;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				flag = 0;
			}
			finally {
				if (null != fileOut) {
					try {
						fileOut.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != bufferImg) {
					bufferImg.flush();
				}
				if (null != wb) {
					try {
						wb.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
		else {
			flag = 0;
		}
		return flag;
	}

	/**
	 * 读取Excel到字符串
	 * 
	 * @param excelPath
	 * @return
	 */
	public static String xls2String(String excelPath) {
		String result = "";
		File file = new File(excelPath);
		if (file.isFile()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				StringBuilder sb = new StringBuilder();
				Workbook rwb = Workbook.getWorkbook(fis);
				Sheet[] sheet = rwb.getSheets();
				for (int i = 0; i < sheet.length; i++) {
					Sheet rs = rwb.getSheet(i);
					for (int j = 0; j < rs.getRows(); j++) {
						Cell[] cells = rs.getRow(j);
						for (int k = 0; k < cells.length; k++)
							sb.append(cells[k].getContents());
					}
				}
				fis.close();
				result += sb.toString();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
