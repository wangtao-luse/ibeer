package com.ibeer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
@PropertySource(value={"classpath:beetl.properties"})
public class AppOnline {
public static void main(String[] args) {
	SpringApplication.run(AppOnline.class, args);
}
@Bean
@LoadBalanced
RestTemplate  restTemplate(){
    return new RestTemplate();
}
}
