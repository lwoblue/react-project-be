package com.react.sample.service.vo;

public class MessageVO {
	
	int uuid;
	int index;
	String sender;
	String recipient;
	String title;
	String content;
	String date;
	
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "MessageVO [uuid=" + uuid + ", index=" + index + ", sender=" + sender + ", recipient=" + recipient
				+ ", title=" + title + ", content=" + content + ", date=" + date + "]";
	}
	
}
