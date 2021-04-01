package com.react.sample.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
		System.out.println("userList 유저리스트 출력 성공");
		
		return userMappr.userList();
	}
	
	@GetMapping("/{keyword}")
	public List<UserVO> searchUser(@PathVariable String keyword) {
		return userMappr.searchUser(keyword);
	}

	@PostMapping
	void insertUser(@RequestBody UserVO user) {
		userMappr.insertUser(user);
	}
	
//	@GetMapping("/{id}")
//	public UserVO fetchUserByID(@PathVariable int id) {
//		System.out.println(userMappr.fatchUserByID(id));
//		UserVO fetcUser = userMappr.fatchUserByID(id);
//		return fetcUser;
//	}
	
	@GetMapping("/personal-information/{email}")
	public UserVO fetchUserByID(@PathVariable String email) {
		System.out.println("fetchUserByID-->" + userMappr.fatchUserByID(email));
		UserVO fetcUser = userMappr.fatchUserByID(email);
		return fetcUser;
	}
	
	@PutMapping("/{id}")
	public void updateUser(@PathVariable int id, @RequestBody UserVO user) {
		
		UserVO updateUser = user;
		
		updateUser.setUserName(user.getUserName());

		userMappr.updateUser(updateUser);
	}
	
//	@DeleteMapping("/{id}")
//	public void deleteUser(@PathVariable int id) {
//		userMappr.deleteUser(id);
//		System.out.println("유저 삭제 성공");
//	}
	
	@DeleteMapping("/{email}")
	public void deleteUser(@PathVariable String email) {
		userMappr.deleteUser(email);
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
			String result = gson.toJson("false");
			return result;
		} else {
			String result = gson.toJson(loginUser);
			return result;
		}
	}
	
	@PostMapping(value="/signUp")
	public String signUp(@RequestBody Map req) {
		Gson gson = new GsonBuilder().create();
		UserVO insertUser = new UserVO();
		
		insertUser.setEmail(String.valueOf(req.get("email")));
		insertUser.setPassword(String.valueOf(req.get("pwd")));
		insertUser.setUserName(String.valueOf(req.get("username")));
		insertUser.setPhotoURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3SvtTRgIX1lfL2YSByB8kwoVkVYQB93It2g&usqp=CAU");
		insertUser.setDeleteYN("n");
		
		try {
			userMappr.signUp(insertUser);
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
				result = gson.toJson("false");
			else
				result = gson.toJson("true");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@PostMapping(value = "/photo/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile multipartFile) {		
        UUID uid = UUID.randomUUID();
//        File targetFile = new File("src/main/resources/profileimgs/"+"userId"+uid.toString()); //    ./ dir
        File targetFile = new File("c:/tmp/"+"userId"+multipartFile.getOriginalFilename()); //    ./ dir
        File dir = new File("c:/tmp");
        // file size 설정 - 제한할것
        
        // field randomUID / originalFileName / binary... / email(forign) / sysdate()
        System.out.println(targetFile);
        try {
        	if(!dir.mkdir()) {
        		dir.delete();
        		targetFile.mkdir();
        	};        	
            InputStream fileStream = multipartFile.getInputStream(); // -> byte type change
            //insert
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            // direct 반환
            // mkdir / file 생성 -> 바로 변환 / 파일명겹치는 경우에 _profile naming

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }

        Map<String, Object> m = new HashMap<>();
        m.put("errorCode", 10);
        return m;
    }
}
