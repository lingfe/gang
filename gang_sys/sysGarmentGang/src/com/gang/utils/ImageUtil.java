package com.gang.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;


/**
 * 图片处理工具类（缩放、水印）
 * 
 * @author liufeng
 * @date 2015-12-17
 */
public class ImageUtil {
	/**
	 * 将图片等比缩放
	 * 
	 * @param targetImg 目标图片
	 * @param resultImg 处理结果
	 * @param w 缩放后的宽度
	 * @param h 缩放后的高度
	 * @return
	 */
	public static boolean imageResize(String targetImg, String resultImg, int w, int h) {
		boolean result = false;
		try {
			// 目标图片
			File targetFile = new File(targetImg);
			Image targetImage = ImageIO.read(targetFile);
			// 目标图片的宽高
			int targetWidth = targetImage.getWidth(null);
			int targetHeight = targetImage.getHeight(null);

			// 得到合适的压缩大小，按比例。
			if (targetWidth >= targetHeight) {
				h = (int) Math.round((targetHeight * w * 1.0 / targetWidth));
			}
			else {
				w = (int) Math.round((targetWidth * h * 1.0 / targetHeight));
			}

			// 构建图片对象
			BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			// 绘制缩小后的图
			image.getGraphics().drawImage(targetImage, 0, 0, w, h, null);
			// 输出到文件流
			FileOutputStream out = new FileOutputStream(resultImg);
			ImageIO.write(image, "jpg", out);
			out.flush();
			out.close();

			result = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加图片水印
	 * 
	 * @param targetImg 目标图片
	 * @param watermarkImg 水印图片
	 * @param resultImg 处理结果
	 * @param x x坐标
	 * @param y y坐标
	 * @param
	 */
	public static boolean addImageWatermark(String targetImg, String watermarkImg, String resultImg, int x, int y) {
		boolean result = false;
		try {
			// 目标图片
			File targetFile = new File(targetImg);
			Image targetImage = ImageIO.read(targetFile);
			// 目标图片的宽高
			int targetWidth = targetImage.getWidth(null);
			int targetHeight = targetImage.getHeight(null);

			// 水印图片
			File watermarkFile = new File(watermarkImg);
			Image watermarkImage = ImageIO.read(watermarkFile);
			// 水印图片的宽高
			int watermarkWidth = watermarkImage.getWidth(null);
			int watermarkHeight = watermarkImage.getHeight(null);

			BufferedImage image = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			// 绘制目标图片
			g.drawImage(targetImage, 0, 0, targetWidth, targetHeight, null);
			// 绘制水印图片
			g.drawImage(watermarkImage, x, y, watermarkWidth, watermarkHeight, null);
			g.dispose();

			OutputStream os = new FileOutputStream(resultImg);
			// 创建编码器，用于编码内存中的图象数据。
			ImageIO.write(image, "jpg", os);
			os.close();

			result = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加文字水印
	 * 
	 * @param targetImg 目标图片
	 * @param text 水印文本
	 * @param resultImg 处理结果
	 * @param x x坐标
	 * @param y y坐标
	 * @return
	 */
	public static boolean addTextWatermark(String targetImg, String text, String resultImg, int x, int y) {
		boolean result = false;
		try {
			// 目标图片
			File targetFile = new File(targetImg);
			Image targetImage = ImageIO.read(targetFile);
			// 目标图片的宽高
			int targetWidth = targetImage.getWidth(null);
			int targetHeight = targetImage.getHeight(null);

			BufferedImage image = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			// 绘制目标图片
			g.drawImage(targetImage, 0, 0, targetWidth, targetHeight, null);

			// 设置字体颜色
			g.setColor(Color.BLACK);
			// 设置字体、字体大小
			g.setFont(new Font("宋体", Font.BOLD, 20));
			// 绘制水印文字
			g.drawString(text, x, y);
			g.dispose();

			OutputStream os = new FileOutputStream(resultImg);
			// 创建编码器，用于编码内存中的图象数据。
//			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
//			en.encode(image);
			ImageIO.write(image, "jpg", os);
			os.close();

			result = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		imageResize("F:/tools/tomcat-7.0.70/webapps/eggs/upload/business_qrcode/29451897-adb4-481f-9032-4835fc071f5b.png", "F:/tools/tomcat-7.0.70/webapps/eggs/upload/business_qrcode/111.png", 240, 240);

		// imageResize("E:/工作文档/九运/head.jpg", "E:/工作文档/九运/head_100.jpg", 100, 100);

		addImageWatermark("F:/tools/tomcat-7.0.70/webapps/eggs/qrcode/template.jpg", "F:/tools/tomcat-7.0.70/webapps/eggs/upload/business_qrcode/111.png", "F:/head_result.jpg", 210, 385);

		// addImageWatermark("E:/工作文档/九运/result.jpg", "E:/工作文档/九运/head_100.jpg", "E:/工作文档/九运/result_1.jpg", 20, 20);

		// addTextWatermark("E:/工作文档/九运/result_1.jpg", "柳峰的别名，测试", "E:/工作文档/九运/result_2.jpg", 130, 75);
	}
}
