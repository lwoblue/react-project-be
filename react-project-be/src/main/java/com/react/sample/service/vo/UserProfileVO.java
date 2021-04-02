package com.react.sample.service.vo;

import java.sql.Blob;

public class UserProfileVO {
	
	String uuid;
	String orgname;
	String userId;
	String regdate;
	String imageFile;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
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
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String array) {
		this.imageFile = array;
	}
	
	
	
}
