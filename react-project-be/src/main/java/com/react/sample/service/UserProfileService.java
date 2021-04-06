package com.react.sample.service;

import java.util.HashMap;

public interface UserProfileService{
	public HashMap<String, Object> selectUserProfile(String id);
	public int selectUserProfileCnt(String id);
	public void updateProfile(HashMap<String, Object> userProfile);
	public void insertProfile(HashMap<String, Object> userProfile);
}
