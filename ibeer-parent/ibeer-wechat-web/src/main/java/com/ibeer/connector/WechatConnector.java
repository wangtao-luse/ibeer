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
import com.ibeer.constant.Constant;
import com.ibeer.dto.Accesstoken;
import com.ibeer.dto.menu.Button;
import com.ibeer.dto.menu.ClickButton;
import com.ibeer.dto.menu.PhotoOrAlbum;
import com.ibeer.dto.menu.SubButton;
import com.ibeer.dto.menu.ViewButton;
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








}
