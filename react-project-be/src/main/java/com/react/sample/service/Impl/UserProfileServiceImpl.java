package com.react.sample.service.Impl;

import org.springframework.stereotype.Service;

import com.react.sample.service.UserProfileService;
import com.react.sample.service.vo.UserProfileVO;

@Service(value="userProfileService")
public class UserProfileServiceImpl implements UserProfileService{

	@Override
	public boolean existProfileImage(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserProfileVO updateProfile(UserProfileVO userProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfileVO insertProfile(UserProfileVO userProfile) {
		// TODO Auto-generated method stub
		return null;
	}
}
