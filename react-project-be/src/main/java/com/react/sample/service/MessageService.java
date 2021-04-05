package com.react.sample.service;

import java.util.List;

import com.react.sample.service.vo.MessageVO;

public interface MessageService{
	public List<MessageVO> messageList(String recipient, String searchInput);
	public MessageVO detailMessage(int uuid);
	public boolean deleteMessage(int uuid);
	public boolean insertMessage(MessageVO message);
}
