package com.react.sample.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.react.sample.service.MainSlideService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MainSlideController {

	@Autowired
	MainSlideService mainSlideService;

	@PostMapping("/slide/searchList")
	public Map<String, Object> searchSlideImageList(@RequestBody Map req) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultData", mainSlideService.searchSlideImageList());
		return resultMap;
	}

	@PostMapping("/slide/download")
	public void updateSlideImageList(@RequestBody Map req) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		mainSlideService.updateSlideImageList(paramMap);
	}

	@PostMapping("/slide/delete")
	public void deleteSlideImageList(@RequestBody Map req) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		mainSlideService.deleteSlideImageList(paramMap);
	}

	@PostMapping(value = "/slide/profile/upload")
	public Map<String, Object> getFileandUpload(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("userId") String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String uid = UUID.randomUUID().toString(); // db처리 주의 -> 길이가 안 맞으면 공백 처리함
//		File targetFile = new File("c:/tmp/" + uid + "-" + multipartFile.getOriginalFilename()); //  dirSystem.out.println(targetFile);
		File targetFile = new File("./profile/" + uid + "-" + multipartFile.getOriginalFilename());

		// file size 설정 - 제한할것
		File dir = new File("./profile");
		try {
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

			FileUtils.copyInputStreamToFile(fileStream, targetFile);

			// insert
			HashMap<String, Object> updateUser = new HashMap<String, Object>();
			updateUser.put("imageFile", multipartFile.getBytes());
			updateUser.put("orgname", multipartFile.getOriginalFilename());
			updateUser.put("userId", id);
			updateUser.put("uuid", (uid + "-" + multipartFile.getOriginalFilename()));
			// data base update or insert

			try {
				// no -> insert
				mainSlideService.insertSlideImageList(updateUser);

				resultMap = mainSlideService.searchSlideImageList();
				return resultMap;
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("result", "error");
				return resultMap;
			}
			// mkdir / file 생성 -> 바로 변환 / 파일명겹치는 경우에 _profile naming
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);
			e.printStackTrace();
			resultMap.put("result", "error");
			return resultMap;
		}
	}
}
