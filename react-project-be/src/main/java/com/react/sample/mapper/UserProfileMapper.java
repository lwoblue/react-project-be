package com.react.sample.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProfileMapper {
	HashMap<String, Object> selectUserProfile(String id);
	int selectUserProfileCnt(String id);	
	void updateProfile(HashMap<String, Object> userProfile);
	void insertProfile(HashMap<String, Object> userProfile);
	HashMap<String, Object> selectphotoURL(String id);
}
