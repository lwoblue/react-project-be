package com.react.sample.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.react.sample.service.UserProfileService;
import com.react.sample.service.vo.UserProfileVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserProfileController {

	@Autowired
	UserProfileService profileService;

	@PostMapping(value = "/users/profile/upload")
//	public String getFileandUpload(@RequestParam("file") MultipartFile multipartFile,@RequestParam("userId") String id) {
	public byte[] getFileandUpload(@RequestParam("file") MultipartFile multipartFile,@RequestParam("userId") String id) {
		Gson gson = new GsonBuilder().create();
		String uid = UUID.randomUUID().toString(); // db처리 주의 -> 길이가 안 맞으면 공백 처리함
//		File targetFile = new File("c:/tmp/" + uid + "-" + multipartFile.getOriginalFilename()); //  dirSystem.out.println(targetFile);
		File targetFile = new File("./profile/" + uid + "-" + multipartFile.getOriginalFilename());
		// file size 설정 - 제한할것
		File dir = new File("./profile");
//		File dir = new File("c:/tmp");
		try {
			System.out.println("dir.mkdir()" + dir.mkdir());
			if (!dir.mkdir()) {// C:/tmp not exist
				dir.delete();
				targetFile.mkdir();
			}
			if (dir.exists()) { // 파일 존재
				File[] files = dir.listFiles();
				System.out.println(files.toString());
				for (int i = 0; i < files.length; i++) {
					if (files[i].delete()) {
						System.out.println(files[i].getName() + " 삭제성공");
					} else {
						System.out.println(files[i].getName() + " 삭제실패");
					}
				}
			} else {
				// 파일 없음
				System.out.println("파일이 존재하지 않습니다.");
			}

			InputStream fileStream = multipartFile.getInputStream();
			// -> byte type change
			byte[] userimage = multipartFile.getBytes();
			String blob = null;
			try {
				blob = userimage.toString();
				System.out.println("blob length: " + blob.length());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// field randomUID / originalFileName / binary... / email(forign) / sysdate()
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			
			// insert
			UserProfileVO updateUser = new UserProfileVO();
			updateUser.setImageFile(blob);
			updateUser.setOrgname(multipartFile.getOriginalFilename());
			System.out.println("multipartFile.getOriginalFilename()::" + multipartFile.getOriginalFilename());
			updateUser.setUserId(id);
			updateUser.setUuid(uid + "-" + multipartFile.getOriginalFilename());
			// data base update or insert
			
			}
			// mkdir / file 생성 -> 바로 변환 / 파일명겹치는 경우에 _profile naming
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);
			e.printStackTrace();
			byte[] a = "error".getBytes();
			return a;
		}
	}
}
