package com.ibeer.account.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ibeer.account.presist.AccountMapper;
import com.ibeer.account.presist.ContractMapper;
import com.ibeer.api.AccountApi;
import com.ibeer.common.req.RequestMessage;
import com.ibeer.common.resp.ResponseMessage;
import com.ibeer.model.Account;

@RestController
public class AccountServiceApi extends ServiceImpl<AccountMapper, Account> implements AccountApi{


	
	

}
