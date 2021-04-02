package com.react.sample.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
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
	public String upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("userId") String id) {
		Gson gson = new GsonBuilder().create();
        String uid = UUID.randomUUID().toString(); // db처리 주의  -> 길이가 안 맞으면 공백 처리함
        File targetFile = new File("c:/tmp/"+"profile_"+multipartFile.getOriginalFilename()); //    ./ dir
        System.out.println(targetFile);
        // file size 설정 - 제한할것        
        File dir = new File("c:/tmp");        
        try {
        	if(!dir.mkdir()) {
        		dir.delete();
        		targetFile.mkdir();
        	}        	        	
            InputStream fileStream = multipartFile.getInputStream(); 
            // -> byte type change            
            byte[] userimage =multipartFile.getBytes();
            String blob = null;
            
            try {
//				 blob = new SerialBlob(userimage);
        		 blob = userimage.toString();
				 System.out.println("blob length: "+blob.length());
			} catch (Exception e1) {
				e1.printStackTrace();
			}            
            
            // field randomUID / originalFileName / binary... / email(forign) / sysdate()
            FileUtils.copyInputStreamToFile(fileStream, targetFile);            
                        
            //insert
            UserProfileVO updateUser = new UserProfileVO();
            updateUser.setImageFile(blob);
            updateUser.setOrgname(multipartFile.getOriginalFilename());
            updateUser.setUserId(id);
            updateUser.setUuid(uid);
            //registration date 
//            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss"); // Date format
//            Date time = new Date();
//            String regiTime = format1.format(time);            
//            updateUser.setRegdate(regiTime);
            // direct 반환 ==> column 추가?
            
            //data base update or insert            
            try {
            	// uid exist?
            	if(profileService.selectUserProfile(id) != null) {
            		// yes -> update
            		profileService.updateProfile(updateUser);            		
            	}else {
            		// no -> insert
            		profileService.insertProfile(updateUser);            		
            	}
    			return gson.toJson("true");
    		} catch (Exception e) {
    			e.printStackTrace();
    			return gson.toJson("false");
    		}
            
            // mkdir / file 생성 -> 바로 변환 / 파일명겹치는 경우에 _profile naming

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }

//        Map<String, Object> m = new HashMap<>();
//        m.put("errorCode", 10);
//        return m;
        return "";
        }
}
