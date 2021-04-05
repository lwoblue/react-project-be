package com.react.sample.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.react.sample.service.vo.MessageVO;

@Mapper
public interface MessageMapper {
	public List<MessageVO> messageList(String recipient, String searchInput);
	public MessageVO detailMessage(int uuid);
	public boolean deleteMessage(int uuid);
	public boolean insertMessage(MessageVO message);
}
