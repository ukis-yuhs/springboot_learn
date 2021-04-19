package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan(basePackages= {"com.example.mapper"}) //扫描mapper
public class SpringbootApacheshiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApacheshiroApplication.class, args);
	}

}
