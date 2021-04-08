package com.dalaoyang.repository;

import com.dalaoyang.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserInfo,Long> {
}
