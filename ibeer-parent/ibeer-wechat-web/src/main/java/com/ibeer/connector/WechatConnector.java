package com.ibeer.connector;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ibeer.common.HttpURLConnectionUtil;
import com.ibeer.constant.Constant;
import com.ibeer.dto.Accesstoken;
import com.ibeer.dto.msg.BaseMessage;
import com.ibeer.dto.msg.Image;
import com.ibeer.dto.msg.ImageMessage;
import com.ibeer.dto.msg.MusicMessage;
import com.ibeer.dto.msg.NewsMessage;
import com.ibeer.dto.msg.TextMessage;
import com.ibeer.dto.msg.VideoMessage;
import com.ibeer.dto.msg.VoiceMessage;
import com.thoughtworks.xstream.XStream;

@Service
public class WechatConnector {
	@Autowired
	private static HttpURLConnectionUtil httpURLConnectionUtil;
	/**
	 *  验证消息的确来自微信服务器
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @param token
	 * @return
	 */
	
	public boolean check(String timestamp, String nonce, String signature,String token) {
		//1.将token、timestamp、nonce三个参数进行字典序排序   
		  String [] str = {token,timestamp,nonce};
		  Arrays.sort(str);
		  //2）将三个参数字符串拼接成一个字符串进行sha1加密 
		  String strs = str[0]+str[1]+str[2];
		  String mysig= sha1(strs);
		  System.out.println("mysig:---->"+mysig);
		  //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		 return mysig.equals(signature);
	}
	 /**
     * 加密处理
     * @param strs
     * @return
     */
	public String sha1(String strs) {
		// TODO Auto-generated method stub
		try {
			//获取加密对象
			MessageDigest md = MessageDigest.getInstance("sha1");
			//加密处理
		    byte[] digest = md.digest(strs.getBytes());
		    //处理加密结果
		    char [] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		    StringBuilder sb = new StringBuilder();
		    for (byte b : digest) {
			 sb.append((chars)[(b>>4)&15]);
			 sb.append(chars[b&15]);
			}
		    return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 解析xml数据包
	 * @param input
	 * @return
	 * @throws DocumentException 
	 */
	public Map<String, String> parseRequest(InputStream input)  {
		Map map = new HashMap<String, String>();
		try {
			SAXReader saxReader = new SAXReader();
			//读取输入流，获取文本对象
			Document read = saxReader.read(input);
			//根据文档对象获取根节点
			Element rootElement = read.getRootElement();
			//获取根节点下的所有节点
			List<Element> elements = rootElement.elements();
			for (Element element : elements) {
				map.put(element.getName(), element.getStringValue());
			}
			input.close();
			System.out.println(map);
		}  catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
/**
 * 处理事件和消息回复
 * @param requestMap
 * @return
 */
 public String getResponse(Map<String,String> requestMap) {
	 String msgType = requestMap.get("MsgType");
	 BaseMessage msg=null;
	 switch (msgType) {
	case "text":
		//处理文本消息
		msg=dealTextMessage(requestMap);
		break;
	case "image":
		msg=dealImageMessage(requestMap);
		break;
	case "voice":
		msg=dealVoiceMessage(requestMap);
		break;
	case "video":
		msg=dealVideoMessage(requestMap);
		break;
	case "shortvideo":
		msg=dealShortvideoMessage(requestMap);
		break;
	case "music":
		msg=dealMusicMessage(requestMap);
		break;
	case "news":
		msg=dealNewsMessage(requestMap);
		break;
	
	case "location":
		msg=dealLocationMessage(requestMap);
		break;
		
	case "link":
		msg=dealLinkMessage(requestMap);
		break;
		

	default:
		break;
		
	}
	//将消息对象处理为数据包
	 
	 String xml = BeeanToXml(msg);
	 return xml;
	 
 }


/**
  * 将消息对象处理为数据包
  * @param msg
  * @return
  */
public String BeeanToXml(BaseMessage msg) {
	 XStream xstream = new XStream();
	 xstream.processAnnotations(TextMessage.class);
	 xstream.processAnnotations(ImageMessage.class);
	 xstream.processAnnotations(VoiceMessage.class);
	 xstream.processAnnotations(VideoMessage.class);
	 xstream.processAnnotations(MusicMessage.class);
	 xstream.processAnnotations(NewsMessage.class);
	 String xml = xstream.toXML(msg);
	 System.out.println(xml);

	return xml;
}
/**
 * 处理文本消息
 * @param requestMap
 * @return
 */
public BaseMessage dealTextMessage(Map<String, String> requestMap) {
	// TODO Auto-generated method stub	
	dealMessage(requestMap);		
	return new TextMessage(requestMap,"hello!");
}
/**
 * 处理发送人和收接收者
 * @param requestMap
 */
public void dealMessage(Map<String, String> requestMap) {
	String temp="";
	temp=requestMap.get("ToUserName");
	String from = requestMap.get("FromUserName");
	requestMap.put("ToUserName", from);
	requestMap.put("FromUserName", temp);
}
/**
 * 处理图片消息
 * @param requestMap
 * @return
 */
public BaseMessage dealImageMessage(Map<String, String> requestMap) {
	// TODO Auto-generated method stub
	dealMessage(requestMap);	
	String mediaId = null;
	return new ImageMessage(requestMap,new Image(mediaId));
}
/**
 * 处理语音消息
 * @param requestMap
 * @return
 */
public BaseMessage dealVoiceMessage(Map<String, String> requestMap) {
	// TODO Auto-generated method stub
	return null;
}
/**
 * 处理视频消息
 * @param requestMap
 * @return
 */
public BaseMessage dealVideoMessage(Map<String, String> requestMap) {
	// TODO Auto-generated method stub
	return null;
}
/**
 * 处理小视频消息
 * @param requestMap
 * @return
 */
public BaseMessage dealShortvideoMessage(Map<String, String> requestMap) {
	// TODO Auto-generated method stub
	return null;
}
/**
 * 处理音乐消息
 * @param requestMap
 * @return
 */
public BaseMessage dealMusicMessage(Map<String, String> requestMap) {
	// TODO Auto-generated method stub
	return null;
}
/**
 *   处理图文消息
 * @param requestMap
 * @return
 */
public BaseMessage dealNewsMessage(Map<String, String> requestMap) {
	// TODO Auto-generated method stub
	return null;
}
/**
 * 处理图文消息
 * @param requestMap
 * @return
 */
public BaseMessage dealLocationMessage(Map<String, String> requestMap) {
	// TODO Auto-generated method stub
	return null;
}
/**
 * 处理链接消息
 * @param requestMap
 * @return
 */

public BaseMessage dealLinkMessage(Map<String, String> requestMap) {
	// TODO Auto-generated method stub
	return null;
}
private  static Accesstoken accesstoken=null;
private void  getToken() {
	
	String serverurl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	serverurl=serverurl.replace("APPID", Constant.APPID).replace("APPSECRET", Constant.APPKEY);	
	String visitGet = httpURLConnectionUtil.visitGet(serverurl, "");
	JSONObject jsonObject =JSONObject.parseObject(visitGet);
	String access_token = jsonObject.getString("access_token");
	String expires_in = jsonObject.getString("expires_in");
	//将token存入
	 accesstoken = new Accesstoken(access_token, expires_in);
}
public String getAccesstoken() {
	if(accesstoken==null||accesstoken.isExpires()) {
		 getToken();
		 return accesstoken.getAccess_token();
	}
	
	return null;
}
}
