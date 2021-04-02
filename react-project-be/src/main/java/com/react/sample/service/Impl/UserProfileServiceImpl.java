package com.react.sample.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.react.sample.mapper.UserProfileMapper;
import com.react.sample.service.UserProfileService;
import com.react.sample.service.vo.UserProfileVO;

@Service
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	UserProfileMapper userProfileMapper;

	@Override
	public UserProfileVO selectUserProfile(String userId) {
		return userProfileMapper.selectUserProfile(userId);
	}

	@Override
	public void updateProfile(UserProfileVO userProfile) {
		userProfileMapper.updateProfile(userProfile);
	}

	@Override
	public void insertProfile(UserProfileVO userProfile) {
		userProfileMapper.insertProfile(userProfile);
	}
}
