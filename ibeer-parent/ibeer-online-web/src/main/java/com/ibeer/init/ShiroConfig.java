package com.ibeer.init;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibeer.realm.CustomRealm;
@Configuration
public class ShiroConfig {
	/**
	 * shiro的过滤器
	 * @param securityManager
	 * @return
	 */
@Bean("shiroFilter")
public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
	ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	shiroFilterFactoryBean.setSecurityManager(securityManager);	
	shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");//未授权界面;
	// 登录成功后要跳转的链接
    shiroFilterFactoryBean.setSuccessUrl("/index");
    // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
    shiroFilterFactoryBean.setLoginUrl("/login");
	/* <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问*/
	Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
	filterChainDefinitionMap.put("/login", "anon");
	filterChainDefinitionMap.put("/admin/**", "authc");
	filterChainDefinitionMap.put("/test/**", "anon");
	 //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
    filterChainDefinitionMap.put("/logout", "logout");   
	/*/主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证*/
    filterChainDefinitionMap.put("/**", "anon");
	shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap );
	return shiroFilterFactoryBean;
}
@Bean
public SecurityManager securityManager() {
    DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
    defaultSecurityManager.setRealm(customRealm());
    return defaultSecurityManager;
}

@Bean
public CustomRealm customRealm() {
    CustomRealm customRealm = new CustomRealm();
    return customRealm;
}
	/*
	 * @Bean public FilterRegistrationBean delegatingFilterProxy(){
	 * FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	 * DelegatingFilterProxy proxy = new DelegatingFilterProxy();
	 * proxy.setTargetFilterLifecycle(true); proxy.setTargetBeanName("shiroFilter");
	 * filterRegistrationBean.setFilter(proxy); return filterRegistrationBean; }
	 */

	/*
	 * @Bean public MethodInvokingFactoryBean getMethodInvokingFactoryBean() {
	 * MethodInvokingFactoryBean methodInvokingFactoryBean=new
	 * MethodInvokingFactoryBean(); methodInvokingFactoryBean.setStaticMethod(
	 * "org.apache.shiro.SecurityUtils.setSecurityManager");
	 * methodInvokingFactoryBean.setArguments(securityManager()); return
	 * methodInvokingFactoryBean; }
	 */


}
