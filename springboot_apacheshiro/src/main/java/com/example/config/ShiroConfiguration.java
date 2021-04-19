package com.example.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.realm.AuthRealm;
import com.example.realm.CredentialMatcher;

@Configuration
public class ShiroConfiguration {
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")SecurityManager manager) {
		ShiroFilterFactoryBean bean =  new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);

		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/ndex");
		bean.setUnauthorizedUrl("/unauthorized");

		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/index", "authc"); //
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/loginUser", "anon");
		filterChainDefinitionMap.put("/admin", "roles[admin]"); //admin角色
		filterChainDefinitionMap.put("/edit", "perms[edit]"); //edit权限
		filterChainDefinitionMap.put("/druid/**", "anon"); //放行所有druid访问
		filterChainDefinitionMap.put("/**", "user"); //是否登录过
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	@Bean("securityManager")
	public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		return manager;
	}

	@Bean("authRealm")
	public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
		AuthRealm authRealm = new AuthRealm();
		//指定使用cache(目前缓存到内容从)
		authRealm.setCacheManager(new MemoryConstrainedCacheManager());
		authRealm.setCredentialsMatcher(matcher);
		return authRealm;
	}
	// 自定义密码比较器
	@Bean("credentialMatcher")
	public CredentialMatcher credentialMatcher() {
		return new CredentialMatcher();
	}

	// shiro与springboot建立关联
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}
}
