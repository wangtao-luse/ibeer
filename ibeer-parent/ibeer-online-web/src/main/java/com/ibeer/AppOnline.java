package com.ibeer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AppOnline {
public static void main(String[] args) {
	SpringApplication.run(AppOnline.class, args);
}
@Bean
@LoadBalanced
RestTemplate  restTemplate(){
	 ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
     RestTemplate restTemplate = new RestTemplate(requestFactory);
	List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    //Add the Jackson Message converter
     MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
     // Note: here we are making this converter to process any kind of response,
     // not only application/*json, which is the default behaviour
     converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
     messageConverters.add(converter);
     restTemplate.setMessageConverters(messageConverters);
     
     
     
     MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
     mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
     restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

     
    
    return restTemplate;
}
}
