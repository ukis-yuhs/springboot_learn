package com.example.service;

import com.example.model.User;

public interface UserService {
	User findByUsername(String username);
}
