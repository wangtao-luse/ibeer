package com.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;

public class TestImageIO {
	 public static void main(String[] args) throws IOException {
		 /***https://www.cnblogs.com/chenhonggao/p/8999447.html
		  * https://blog.csdn.net/baidu_28665563/article/details/82887485
		  * 1.public final class ImageIO extends Object
		  * 2.常用方法
		  *   1)ImageIO.read(File input);
		  *   2)ImageIO.read(InputStream stream);
		  *   3)ImageIO.read(ImageInputStream stream);
		  *   4)ImageIO.read(URL input);
		  *   5)public int[] getRGB(int startX,int startY)		
		  *   	返回默认RGB颜色模型（TYPE_INT_ARGB）和默认sRGB颜色空间中的整数像素
		  *   6)ImageIO.write(RenderedImage im, String formatName, File output);
		  *   7)ImageIO.write(RenderedImage im, String formatName, ImageOutputStream output);
		  *   8)ImageIO.write(RenderedImage im, String formatName, OutputStream output);
		  *   
		  */
		 
		 
		 /**
		  * 1.public class BufferedImage extends Image implements WritableRenderedImage, Transparency
		  * 2.构造函数
		  *   1）BufferedImage(int width, int height, int imageType);构造一个BufferedImage预定义的图像类型。
		  */
		   
		  
		 test0();
		 
	}
	 
	public static void test0() throws IOException {
		//1)ImageIO.read(File input);
		String path = "D:\\temp\\imgs\\tmp\\index1.jpg";
		File file = new File(path);
		BufferedImage im1 = ImageIO.read(file);
		//2)ImageIO.read(InputStream stream);
		FileInputStream fs = new FileInputStream(file);
		 BufferedImage im2 = ImageIO.read(fs);
		//3)ImageIO.read(ImageInputStream stream);
		 ImageInputStream iis = new FileImageInputStream(file);
		 BufferedImage im3 = ImageIO.read(iis);
		 //4)ImageIO.read(URL input);
		 String encode = URLEncoder.encode(path, "utf-8");
         URL url = new URL("file:///" + encode);
		 BufferedImage im4 = ImageIO.read(fs);
		 //6)ImageIO.write(RenderedImage im, String formatName, File output);
		 ImageIO.write(im1, "jpg", file);
		 //ImageIO.write(RenderedImage im, String formatName, ImageOutputStream output)
		 ImageIO.write(im1,"jpg",new FileImageOutputStream(file));
		 //ImageIO.write(RenderedImage im, String formatName, OutputStream output);
		 ImageIO.write(im1,"jpg",new FileOutputStream(file));
	}
	 //读取本地文件
	public void test1() {
		
	}

}
