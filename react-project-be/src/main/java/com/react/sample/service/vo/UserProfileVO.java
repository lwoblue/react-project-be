package com.react.sample.service.vo;

import java.io.InputStream;
import java.util.UUID;

public class UserProfileVO {
	
	UUID uuid;
	String orgname;
	String userId;
	String regdate;
	InputStream imageFile;
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public InputStream getImageFile() {
		return imageFile;
	}
	public void setImageFile(InputStream imageFile) {
		this.imageFile = imageFile;
	}
	
	
	
}
