package com.example.utils;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义加密方法
 */
public class MyPasswordEncoder implements PasswordEncoder {

	/**
	 * 加密
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(rawPassword);
	}

	/**
	 * 匹配
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
		return encoder.matches(rawPassword, encodedPassword);
	}

}
