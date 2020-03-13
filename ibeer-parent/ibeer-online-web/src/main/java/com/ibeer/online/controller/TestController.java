package com.ibeer.online.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Constant;
import com.ibeer.api.TestApi;
import com.ibeer.common.constant.ConstantBase;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.dto.ImageUtil;
import com.ibeer.model.Contract;

@Controller
public class TestController {
	@Autowired
    RestTemplate restTemplate;
	//@Autowired
	//TestApi testApi;

@RequestMapping("test/hello")
@ResponseBody
public String hello() {
	return "hello";
}
@ResponseBody
@RequestMapping(value = "test/getTest",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getResponseMessage(@RequestBody(required = false) JSONObject jsonObject,HttpServletRequest request) {
	   ResponseMessage responseMessage = ResponseMessage.getSucess();
	   //fegin rest
	   String url="http://ibeer-account-service/account/test";	   
	    responseMessage = restTemplate.getForObject(url, JSONObject.class).toJavaObject(ResponseMessage.class);
	   
	  
	   return responseMessage.add("wangtao");
}


@RequestMapping(value = "test/getMsg",produces = {"application/json;charset=UTF-8"})
@ResponseBody
public ResponseMessage testFegin() {
	ResponseMessage resp = ResponseMessage.getSucess();
	resp.setReturnResult("333");
	//ResponseMessage testFegin = testApi.testFegin();
	
	return null;
}
/**
 * @param @return 参数说明
 * @return BaseRestResult 返回类型
 * @Description: 生成滑块拼图验证码
 * https://blog.csdn.net/a183400826/article/details/90752724?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
 */
@RequestMapping(value = "/getImageVerifyCode", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
@ResponseBody
public ResponseMessage getImageVerifyCode(HttpServletRequest request) {
    Map<String, Object> resultMap = new HashMap<>();
    //读取本地路径下的图片,随机选一条
    String path = this.getClass().getResource("/static/img/code").getPath();
    File file = new File(path);
    File[] files = file.listFiles();
    int n = new Random().nextInt(files.length);
    File imageUrl = files[n];
    ImageUtil.createImage(imageUrl, resultMap);

    //读取网络图片
   // ImageUtil.createImage("http://hbimg.b0.upaiyun.com/7986d66f29bfeb6015aaaec33d33fcd1d875ca16316f-2bMHNG_fw658",resultMap);
    request.getSession().setAttribute("xWidth", resultMap.get("xWidth"));
    resultMap.remove("xWidth");
    resultMap.put("errcode", 0);
    resultMap.put("errmsg", "success");
    return new ResponseMessage(resultMap);
}


/**
 * 校验滑块拼图验证码
 *
 * @param moveLength 移动距离
 * @return BaseRestResult 返回类型
 * @Description: 生成图形验证码
 */
@RequestMapping(value = "/verifyImageCode", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
@ResponseBody
public ResponseMessage verifyImageCode(@RequestParam(value = "moveLength") String moveLength,HttpServletRequest request) {
    Double dMoveLength = Double.valueOf(moveLength);
    Map<String, Object> resultMap = new HashMap<>();
    try {
        Integer xWidth = (Integer) request.getSession().getAttribute("xWidth");
        if (xWidth == null) {
            resultMap.put("errcode", 1);
            resultMap.put("errmsg", "验证过期，请重试");
            return new ResponseMessage(resultMap);
        }
        if (Math.abs(xWidth - dMoveLength) > 10) {
            resultMap.put("errcode", 1);
            resultMap.put("errmsg", "验证不通过");
        } else {
            resultMap.put("errcode", 0);
            resultMap.put("errmsg", "验证通过");
        }
    } catch (Exception e) {
        return ResponseMessage.getFailed(ConstantBase.FAILED_SYSTEM_ERROR);
    } finally {
    	request.getSession().removeAttribute("xWidth");
    }
    return new ResponseMessage(resultMap);
}
@RequestMapping(value = "/sendCode", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
@ResponseBody
public ResponseMessage sendCode(@RequestParam(value = "phone") String phone,HttpServletRequest request) {
    Map<String, Object> resultMap = new HashMap<>();
    try {
          Random r = new Random();
          int nextInt = r.nextInt(999999)+100000;
          String sendCode = String.valueOf(nextInt);
          
          request.getSession().setAttribute("sendCode", sendCode+phone);
        if (StringUtils.isEmpty(phone)) {
            resultMap.put("errcode", 1);
            resultMap.put("errmsg", "手机号码不能为空！");
            return new ResponseMessage(resultMap);
        }
       System.out.println("验证码为：-------------------》"+sendCode);
    } catch (Exception e) {
        return ResponseMessage.getFailed(ConstantBase.FAILED_SYSTEM_ERROR);
    } finally {
    	request.getSession().removeAttribute("sendCode");
    }
    return new ResponseMessage(resultMap);
}
}
