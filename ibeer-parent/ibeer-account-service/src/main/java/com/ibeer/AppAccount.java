package com.ibeer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.ibeer.account.presist")
public class AppAccount {
	public static void main(String[] args) {
		SpringApplication.run(AppAccount.class, args);
	}

}
