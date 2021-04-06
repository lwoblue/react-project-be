package com.react.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.react.sample.service.vo.UserVO;

@Mapper
public interface UserMapper {
	
	List<UserVO> userList();
	List<UserVO> searchUser(String keyword);
	UserVO searchUserByName(String name);
//	UserVO fatchUserByID(int id);
	UserVO fatchUserByID(String email);
	
	void updateUser(UserVO user);
	void insertUser(UserVO user);
//	void deleteUser(int id);
	void deleteUser(String email);
	
	UserVO login(UserVO userVO);
	void signUp(UserVO user);
	UserVO duplicateCheck(String email);
}
