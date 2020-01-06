package com.ibeer.account.service;



import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ibeer.account.presist.AccountMapper;
import com.ibeer.account.presist.CompanyInfoMapper;
import com.ibeer.account.presist.OauthMapper;
import com.ibeer.api.AccountApi;
import com.ibeer.common.BaseException;
import com.ibeer.common.constant.ConstantBase;
import com.ibeer.common.req.RequestMessage;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.model.account.Account;
import com.ibeer.model.account.CompanyInfo;
import com.ibeer.model.account.Oauth;
import com.ibeer.util.DateUtil;
import com.ibeer.util.MD5;

@RestController
@RequestMapping("/account")
public class AccountServiceApi extends ServiceImpl<AccountMapper, Account> implements AccountApi{
   @Autowired
	AccountMapper accountMapper;
   @Autowired
    OauthMapper oauthMapper;
   @Autowired
    CompanyInfoMapper companyInfoMapper;
    /**注册提交
     * 1.向用户表(t_a_account),用户认证表(T_A_OAUTH),公司表(T_A_COMPANY_INFO)添加一条数据;     *   
     *   a.查询t_a_account表(根据MAX(ID),如果为null,UID为10000,否则UID=10000+ID;
     *   b.插入T_A_OAUTH表;(密码(MD5)和手机(Base64)需要加密)
     *   c.如果是企业注册插入T_A_COMPANY_INFO;
     */
	@Override
	@Transactional
	@RequestMapping("/regSub")
	public ResponseMessage regSub(RequestMessage requestMessage) {
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
			//加密密码MD5
			String credential = oauth.getCredential();
			String md5 = MD5.getInstance().getMD5(uid+credential);
			oauth.setCredential(md5);			
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


	
	

}
