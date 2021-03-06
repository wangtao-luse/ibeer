package com.ibeer.common.resp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.ibeer.common.constant.ConstantBase;


public final class ResponseMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 00:失败
	 * 10:成功
	 */
	private String resultCode;
	private String resultMessage;
	private Map<String, Object> returnResult = new HashMap<String, Object>();
	public ResponseMessage() {
	}
	public ResponseMessage(String resultCode, String resultMessage) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}
	public ResponseMessage(Map map) {
		super();
		this.resultCode = ConstantBase.SUCCESS_CODE;
		this.resultMessage = ConstantBase.SUCESS_MESSAGE;
		this.returnResult=map;
	}
	public ResponseMessage(Object object) {
		super();
		this.resultCode = ConstantBase.SUCCESS_CODE;
		this.resultMessage = ConstantBase.SUCESS_MESSAGE;
		this.returnResult.put("result", object);
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public Map<String, Object> getReturnResult() {
		return returnResult;
	}
	public void setReturnResult(Map<String, Object> returnResult) {
		this.returnResult = returnResult;
	}

	 //添加可变参数注意是为了解决Could not extract response: no suitable HttpMessageConverter found for respose type
	//具体原因不清楚。。。。。待解决
	  public void setReturnResult(Object object) { 
		  Map<String,Object> map = new  HashMap<String, Object>();
		  map.put("result", object);
		  this.returnResult = map;
	  }
	  
	 
	public void setReturnResult(String key, Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, object);
		this.returnResult = map;
	}

	public  ResponseMessage add(Object object) {
		this.returnResult.put("data", object);
		return this;
	}

	public ResponseMessage add(String key, Object object) {
	     returnResult.put(key, object);
		return this;
	}

	public static ResponseMessage getSucess() {
		return new ResponseMessage(ConstantBase.SUCCESS_CODE, ConstantBase.SUCESS_MESSAGE);
	}
	public static ResponseMessage getSucess(String successMsg) {
		return new ResponseMessage(ConstantBase.SUCCESS_CODE, successMsg);
	}

	public static ResponseMessage getFailed() {
		return new ResponseMessage(ConstantBase.FAILED_CODE, ConstantBase.FAILED_MESSAGE);
	}
	public static ResponseMessage getFailed(String faileMsg) {
		return new ResponseMessage(ConstantBase.FAILED_CODE, faileMsg);
	}

}
