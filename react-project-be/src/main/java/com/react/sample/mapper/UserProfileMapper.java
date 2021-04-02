package com.react.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.react.sample.service.vo.UserProfileVO;

@Mapper
public interface UserProfileMapper {
	UserProfileVO selectUserProfile(String id);
	void updateProfile(UserProfileVO userProfile);
	void insertProfile(UserProfileVO userProfile);
}
