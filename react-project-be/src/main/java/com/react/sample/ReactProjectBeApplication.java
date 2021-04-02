package com.react.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.react.sample.mapper")
public class ReactProjectBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactProjectBeApplication.class, args);
	}

}
