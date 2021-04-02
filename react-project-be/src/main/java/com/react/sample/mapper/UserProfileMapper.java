package com.react.sample.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.react.sample.service.vo.UserProfileVO;

@Mapper
@Repository
public interface UserProfileMapper {
	boolean existProfileImage(String id);
	UserProfileVO updateProfile(UserProfileVO userProfile);
	UserProfileVO insertProfile(UserProfileVO userProfile);
}
