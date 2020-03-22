package com.ibeer.common.wechat;


import java.util.Map;

import org.springframework.stereotype.Service;

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
public class MessageUtil {
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
			
		case "event":
			msg=dealEventMessage(requestMap);
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
		String text="精酿啤酒的世界绚丽多彩,每一杯精酿啤酒给你的不仅仅是啤酒的口感,更多的是啤酒背后的历史、地理、人文和酿酒师的匠心。"
				+ "让我们一起打开人生新的窗户，走进精酿的世界吧！";
		return new TextMessage(requestMap,text);
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
	/**
	 * 处理事件推送
	 * @param requestMap
	 * @return
	 */
	private BaseMessage dealEventMessage(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		String key = requestMap.get("Event");
		switch (key) {
		case "CLICK":
			 dealClickEvent(requestMap);
			break;
		case "VIEW":
				dealViewEvent(requestMap);
				break;

		default:
			break;
		}
		return null;
	}
	private void dealViewEvent(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		String key = requestMap.get("EventKey");//创建菜单时菜单的key;
		switch (key) {
		case "1-1"://第一个一级裁断
			
			break;

		default:
			break;
		}
		
	}
	private void dealClickEvent(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		
	}

}
