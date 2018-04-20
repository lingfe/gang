package com.gang.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 二维码工具类
 * 
 * @author liufeng
 * @date 2016-09-28
 */
public class QRCodeUtil {
	/**
	 * 根据文本创建二维图片
	 * 
	 * @param content 文本内容
	 * @param pngFile png二维码
	 * @return
	 */
	public static boolean createQRCode(String content, File pngFile) {
		boolean result = true;
		
		Qrcode qrcode = new Qrcode();
		// 纠错等级（四种等级）
		qrcode.setQrcodeErrorCorrect('M');
		// N代表数字，A代表a-Z，B代表其他字符
		qrcode.setQrcodeEncodeMode('B');
		// 版本
		qrcode.setQrcodeVersion(7);

		// 设置二维码的大小公式：67 + 12 * （version - 1）
		int width = 67 + 12 * (7 - 1);
		int height = 67 + 12 * (7 - 1);

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufferedImage.createGraphics();

		// 设置背景颜色（白色）
		gs.setBackground(Color.WHITE);
		// 设置图像颜色（黑色）
		gs.setColor(Color.BLACK);
		// 清除画板的内容
		gs.clearRect(0, 0, width, height);

		// 添加一个偏移量
		int pixoff = 2;

		try {
			byte[] data = content.getBytes("gb2312");
			if (data.length > 0 && data.length < 120) {
				boolean[][] s = qrcode.calQrcode(data);
	
				for (int i = 0; i < s.length; i++) {
					for (int j = 0; j < s.length; j++) {
						if (s[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			}
			else {
				result = false;
			}
	
			gs.dispose();
			bufferedImage.flush();
			// 生成二维码图片到磁盘
			ImageIO.write(bufferedImage, "png", pngFile);
		} catch(Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String qrcodePath = null;
		// 生成订单二维码
		String fileName = String.format("%s.png", "ttest");
		File pngFile = new File("F:\\banya", fileName);
		boolean result = QRCodeUtil.createQRCode(DesUtil.encrypt("111"), pngFile);
		// 二维码创建成功
		if (result) {
			qrcodePath = String.format("qrcode/rent/%s", fileName);
		}
		System.out.println(qrcodePath);
	}
}
