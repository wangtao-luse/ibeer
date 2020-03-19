package com.ibeer.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class HttpURLConnectionUtil {
	public static void main(String[] args) {
		//1.获取访问的地址
		//2.得到网络访问对象
		//3.设置请求参数
		//4.得到响应状态码的返回值 
		//5.如果返回值正常，数据在网络中是以流的形式得到服务端返回的数
		//6.断开连接，释放资源
		
	}
    /**
     * HttpURLConnection接口调用
     * @param serverurl 服务地址
     * @param requesturl 请求地址
     * @param json    请求数据
     * @return  返回字符串
     */
	public static String visitPost(String serverurl,String requesturl,String json) {
		HttpURLConnection http =null;
		String result = null;
		try {
			String p=serverurl+requesturl;
			//1.获取访问的地址
			URL url = new URL(p);
			//2.得到网络访问对象
			 http= (HttpURLConnection)url.openConnection();
			//3.设置请求参数
			//3.1设置请求方式
			http.setRequestMethod("POST");
			//3.2设定传送的内容类型
			http.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			//3.3设置是否从httpUrlConnection读入，默认情况下是true;
			http.setDoInput(true);
			//3.4设置是否向HttpURLConnection输出, 默认情况下是false;(输出参数)			
			http.setDoOutput(true);
			//https://www.cnblogs.com/ColdWindBlows/p/11721245.html
			// System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			// System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			//3.5连接超时30秒
			http.setConnectTimeout(30000);
			//3.6读取超时30秒
			http.setReadTimeout(30000);
			//连接
			http.connect();
			//写入参数到请求中
			OutputStream os = http.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.flush();
			os.close();
			
			//4.得到响应状态码的返回值 
			int responseCode = http.getResponseCode();
			//5.如果返回值正常，数据在网络中是以流的形式得到服务端返回的数
			StringBuffer sb = new StringBuffer();
			
			 if("200".equals(responseCode)) {
				InputStream inputStream = http.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
				String readLine = "";
				
				while((readLine=br.readLine())!=null) {
					if(readLine.length()>0) {
						//trim()去除字符串头尾空白字符
						sb.append(readLine.trim());
					}
				}
			 }
			 if(null!=sb) {
				 result=sb.toString();
			 }
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			//6.断开连接，释放资源
			 http.disconnect();
		}
		
      return result;
	}
public static String  visitGet(String serverurl,String requesturl,String json) {
	String result = null;
	HttpURLConnection http=null;
	try {
		String p=serverurl+requesturl;
		//1.获取访问的地址
		URL url = new URL(p);
		//2.得到网络访问对象
		 http = (HttpURLConnection)url.openConnection();
		//3.设置请求参数
		//3.1设置请求方式
		http.setRequestMethod("GET");
		//3.2设定传送的内容类型
		http.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		//3.3设置是否从httpUrlConnection读入，默认情况下是true;
		http.setDoInput(true);
		//3.4设置是否向HttpURLConnection输出, 默认情况下是false;(输出参数)			
		http.setDoOutput(false);
		//3.5连接超时30秒
		http.setConnectTimeout(30000);
		//3.6读取超时30秒
		http.setReadTimeout(30000);
		//连接
		http.connect();
		//4.得到响应状态码的返回值 
		int responseCode = http.getResponseCode();
		//5.如果返回值正常，数据在网络中是以流的形式得到服务端返回的数
		StringBuffer sb = new StringBuffer();
		 if("200".equals(responseCode)) {
				InputStream inputStream = http.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
				String readLine = "";
				
				while((readLine=br.readLine())!=null) {
					if(readLine.length()>0) {
						//trim()去除字符串头尾空白字符
						sb.append(readLine.trim());
					}
				}
			 }
		 if(null!=sb) {
			 result=sb.toString();
		 }
		
		
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		//6.断开连接，释放资源
		 http.disconnect();
	}
	return result;
}

}
