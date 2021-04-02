package com.react.sample.service;

import com.react.sample.service.vo.UserProfileVO;

public interface UserProfileService{
	public UserProfileVO selectUserProfile(String id);
	public void updateProfile(UserProfileVO userProfile);
	public void insertProfile(UserProfileVO userProfile);
}
