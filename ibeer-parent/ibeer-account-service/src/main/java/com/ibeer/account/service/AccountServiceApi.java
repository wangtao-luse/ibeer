package com.ibeer.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibeer.account.presist.ContractMapper;
import com.ibeer.model.Contract;

@RestController
public class AccountServiceApi {
	@Autowired
	ContractMapper contractMapper;
@RequestMapping("/getContract")
 public Contract  hello() {
	 Contract queryById = contractMapper.queryById(new Long("179")); 
	 return queryById;
 }

}
