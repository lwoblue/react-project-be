package com.react.sample.service;

import com.react.sample.service.vo.UserProfileVO;

public interface UserProfileService{
	public boolean existProfileImage(String id);
	public UserProfileVO updateProfile(UserProfileVO userProfile);
	public UserProfileVO insertProfile(UserProfileVO userProfile);
}
