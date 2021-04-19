package com.example.mapper;

import org.apache.ibatis.annotations.Param;

import com.example.model.User;

public interface UserMapper {

	User findByUsername(@Param("username") String username);
}
