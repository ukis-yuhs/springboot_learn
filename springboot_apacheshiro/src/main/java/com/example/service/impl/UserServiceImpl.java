package com.example.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

}
