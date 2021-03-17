package com.react.sample.web;

import java.util.List;

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
	
	@PostMapping
	void insertUser(@RequestBody UserVO user) {
		userMappr.insertUser(user);
	}
	
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
}
