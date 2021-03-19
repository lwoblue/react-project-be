package com.react.sample.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.react.sample.mapper.UserMapper;
import com.react.sample.service.vo.UserVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserMapper userMappr;
	
	@GetMapping
	public List<UserVO> userList(){
		System.out.println(userMappr.userList());
		System.out.println("유저리스트 출력 성공");
		
		return userMappr.userList();
	}
	
//	@PostMapping
//	void insertUser(@RequestBody UserVO user) {
//		userMappr.insertUser(user);
//	}
	
	@GetMapping("/{id}")
	public UserVO fetchUserByID(@PathVariable int id) {
		System.out.println(userMappr.fatchUserByID(id));
		UserVO fetcUser = userMappr.fatchUserByID(id);
		return fetcUser;
	}
	
	@PutMapping("/{id}")
	public void updateUser(@PathVariable int id, @RequestBody UserVO user) {
		
		UserVO updateUser = user;
		
		System.out.println("업데이트 유저 ----->" + updateUser);
		
		updateUser.setFirstName(user.getFirstName());
		updateUser.setLastName(user.getLastName());
		updateUser.setAge(user.getAge());
		updateUser.setSalary(user.getSalary());

		userMappr.updateUser(updateUser);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		userMappr.deleteUser(id);
		System.out.println("유저 삭제 성공");
	}
	

	@PostMapping(value="/login")
	public String login(@RequestBody Map req) {
		Gson gson = new GsonBuilder().create();
		UserVO user = new UserVO();
		
		user.setEmail(String.valueOf(req.get("email")));
		user.setPassword(String.valueOf(req.get("pwd")));
		
		UserVO loginUser = userMappr.login(user);
		
		if(loginUser == null) {
			String result = gson.toJson("로그인에 실패했습니다.");
			return result;
		} else {
			String result = gson.toJson(loginUser);
			return result;
		}
	}
	
	@PostMapping(value="/signUp")
	public String insertUser(@RequestBody Map req) {
		Gson gson = new GsonBuilder().create();
		UserVO inserUser = new UserVO();
		
		inserUser.setEmail(String.valueOf(req.get("email")));
		inserUser.setPassword(String.valueOf(req.get("pwd")));
		inserUser.setUserName(String.valueOf(req.get("username")));
		
		try {
			userMappr.insertUser(inserUser);
			return gson.toJson("true");
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson("false");
		}
	}
	
	@PostMapping(value="/duplicateCheck")
	public String duplicateCheck(@RequestBody Map req) {
		Gson gson = new GsonBuilder().create();
		UserVO user = new UserVO();
		String result = null;
		
		user.setEmail(String.valueOf(req.get("email")));
		
		try {
			user = userMappr.duplicateCheck(user.getEmail());
			if(user != null)
				result = gson.toJson("true");
			else
				result = gson.toJson("false");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
