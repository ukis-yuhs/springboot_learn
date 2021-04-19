package com.example.controller;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true) //启动了@PreAuthorize注解
public class DemoController {

	@RequestMapping("/")
	public String home() {
		return "hello spring boot";
	}

	/**
	 * 需要认证访问
	 * @return
	 */
	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}

	/**
	 * 需要权限访问
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')") // 调用前认证
//	@PostAuthorize("hasRole('ROLE_ADMIN')") // 调用后认证
//	@PreFilter("") //集合类型进行验证
//	@PostFilter("") //集合类型进行验证
	@RequestMapping("/roleAuth")
	public String roleAuth() {
		return "admin auth";
	}

	@PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')")
	@PostAuthorize("returnObjcet%2==0")
	@RequestMapping("/test")
	public Integer test(Integer id, String userName, User user) {
		return id;
	}

	@PreFilter("filterObjcet%2==0")
	@PostFilter("filterObjcet%4==0")
	@RequestMapping("/test2")
	public List<Integer> test2(List<Integer> idList) {
		return idList;
	}

}
