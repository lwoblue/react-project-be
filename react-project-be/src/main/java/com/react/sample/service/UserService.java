package com.react.sample.service;

import java.util.Map;

import org.springframework.stereotype.Service;


public interface UserService {
	public Map<String, Object> selectImageFile(Map<String, Object> fileParam) throws Exception;
}
