package com.react.sample.service.Impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.react.sample.mapper.UserProfileMapper;
import com.react.sample.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	UserProfileMapper userProfileMapper;

	@Override
	public HashMap<String, Object> selectUserProfile(String userId) {
		return userProfileMapper.selectUserProfile(userId);
	}

	@Override
	public int selectUserProfileCnt(String userId) {
		return userProfileMapper.selectUserProfileCnt(userId);
	}
	
	@Override
	public void updateProfile(HashMap<String, Object> userProfile) {
		userProfileMapper.updateProfile(userProfile);
	}

	@Override
	public void insertProfile(HashMap<String, Object> userProfile) {
		userProfileMapper.insertProfile(userProfile);
	}

	@Override
	public HashMap<String, Object> selectphotoURL(String userId) {
		// TODO Auto-generated method stub
		return userProfileMapper.selectphotoURL(userId);
	}
}
