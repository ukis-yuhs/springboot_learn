package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.service.MyUserService;
import com.example.utils.MyPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringbootSpringSecurityConfig extends WebSecurityConfigurerAdapter {

	//注入UserService
	@Autowired
	private MyUserService myUserService;

	/**
	 * 身份验证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
		// 基于内存的验证
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");

		// 基于数据库的验证
		auth.userDetailsService(myUserService).passwordEncoder(new MyPasswordEncoder());

		// 使用默认表结构
		//auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("").passwordEncoder(new MyPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and()
			.logout().permitAll()
			.and()
			.formLogin();
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
	}




}
