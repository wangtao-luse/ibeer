package com.ibeer.account.service;



import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctc.wstx.util.StringUtil;
import com.ibeer.account.presist.AccountMapper;
import com.ibeer.account.presist.CompanyInfoMapper;
import com.ibeer.account.presist.LoginListMapper;
import com.ibeer.account.presist.OauthMapper;
import com.ibeer.api.AccountApi;
import com.ibeer.common.BaseException;
import com.ibeer.common.constant.ConstantBase;
import com.ibeer.common.req.RequestMessage;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.model.account.Account;
import com.ibeer.model.account.CompanyInfo;
import com.ibeer.model.account.LoginList;
import com.ibeer.model.account.Oauth;
import com.ibeer.util.AddressUtils;
import com.ibeer.util.DateUtil;
import com.ibeer.util.JMMD5;
import com.ibeer.util.MD5;
import com.ibeer.util.PBKDF2Util;

@RestController
@RequestMapping("/account")
public class AccountServiceApi extends ServiceImpl<AccountMapper, Account> implements AccountApi{
   @Autowired
	AccountMapper accountMapper;
   @Autowired
    OauthMapper oauthMapper;
   @Autowired
    CompanyInfoMapper companyInfoMapper;
   @Autowired
   LoginListMapper loginListMapper; 
    /**注册提交
     * 1.向用户表(t_a_account),用户认证表(T_A_OAUTH),公司表(T_A_COMPANY_INFO)添加一条数据;     *   
     *   a.查询t_a_account表(根据MAX(ID),如果为null,UID为10000,否则UID=10000+ID;
     *   b.插入T_A_OAUTH表;(密码(MD5)和手机(Base64)需要加密)
     *   c.如果是企业注册插入T_A_COMPANY_INFO;
     */
	@Override
	@Transactional
	@RequestMapping("/regSub")
	public ResponseMessage regSub(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Oauth oauth = jsonObject.toJavaObject(Oauth.class);
			String utype = jsonObject.getString("utype");
			//得到UID
			Integer accountid = accountMapper.getAccountMaxId();
			Integer start=10000;
			String uid="10000";
			if(null!=accountid) {
			  Integer result = start+accountid;
			  uid = result.toString();	
			}
			//插入用户表数据
			Account account = new Account();
			account.setUid(uid);			
			account.setCreatedate(DateUtil.setDate(new Date()));
			account.setCreateip(requestMessage.getRequestHeader().getRemoteAddr());
			account.setStatus("1");
			account.setUtype(utype);
			account.setSex("1");		
			accountMapper.insert(account);
			//插入用户认证表数据
			oauth.setUId(uid);
			oauth.setOauthType("phone");
			//加密密码
			String salt = UUID.randomUUID().toString();	
			oauth.setPwd(salt);
			String credential = oauth.getCredential();
			String result = new SimpleHash("MD5", credential, ByteSource.Util.bytes(salt), 1024).toString();
			
			oauth.setCredential(result);			
			//加密手机号码	Base64		
			String encodeToString = Base64.getEncoder().encodeToString(oauth.getOauthId().getBytes());
			oauth.setOauthId(encodeToString);
			oauthMapper.insert(oauth);	
			//插入公司信息表
			if("2".equals(utype)) {
				CompanyInfo companyInfo = jsonObject.toJavaObject(CompanyInfo.class);
				            companyInfo.setUId(uid);
				companyInfoMapper.insert(companyInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(ConstantBase.FAILED_SYSTEM_ERROR);
		}
		
		return ResponseMessage.getSucess();
	}
   
   /**登录提交
    * 1.使用用户名去用户信息认证表(T_A_OAUTH)查看是否有对应的记录;
    * 2.有记录,插入登录记录表(T_A_LOGIN_LIST)信息;
    * 
    */
   @RequestMapping("/login")   
   @Transactional
   @Override
   public ResponseMessage login(@RequestBody RequestMessage requestMessage) {
	   ResponseMessage responseMessage = ResponseMessage.getSucess();
	   try {
		   JSONObject jsonObject = requestMessage.getBody().getContent();
		   Oauth oauth = jsonObject.toJavaObject(Oauth.class);
		   //验证用户名和密码
		   QueryWrapper<Oauth> queryWrapper = new QueryWrapper<Oauth>();
		   queryWrapper.eq("OAUTH_ID", oauth.getOauthId());
		   Oauth selectOne = oauthMapper.selectOne(queryWrapper);
		   //插入记录表
		   if(null!=selectOne) {
			   LoginList loginList = new LoginList();
			   loginList.setLoginTime(DateUtil.setDate(new Date()));
			   loginList.setLoginIp(requestMessage.getRequestHeader().getRemoteAddr());	
			   if(!StringUtils.isEmpty(selectOne.getUId())) {
				   loginList.setUId(selectOne.getUId());
			   }
			   String remoteAddr = requestMessage.getRequestHeader().getRemoteAddr();
			   if(!"127.0.0.1".equals(remoteAddr)) {
				   String address =AddressUtils.getAddress(remoteAddr); 
				   if(!StringUtils.isEmpty(address)) {
						      loginList.setLoginIpLookup(address);
						  
					 }
			   }else {
				   loginList.setLoginIpLookup(remoteAddr);
			   }
				  
				  
			   loginListMapper.insert(loginList);
			   responseMessage.setReturnResult(selectOne);
		   }else {
			   
			   throw new BaseException("用户名或密码不正确");
		   }
		  
	}catch (BaseException e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(e.getMessage());
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(ConstantBase.FAILED_SYSTEM_ERROR);
	}
	   
	   return responseMessage;
   }
	
	public ResponseMessage queryByOauthId(@RequestBody RequestMessage requestMessage) {
		String oauthId = requestMessage.getBody().getOauthId();
		QueryWrapper<Oauth> queryWrapper = new QueryWrapper<Oauth>();
		queryWrapper.eq("OAUTH_ID", oauthId);
		oauthMapper.selectOne(queryWrapper);
		return null;
	}
	

}
