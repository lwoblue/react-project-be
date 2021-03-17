package com.react.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.react.sample.service.vo.UserVO;

@Mapper
public interface UserMapper {
	
	List<UserVO> userList();
	UserVO fatchUserByID(int id);
	
	void updateUser(UserVO user);
	void insertUser(UserVO user);
	void deleteUser(int id);
}
