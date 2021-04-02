package com.react.sample.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.react.sample.mapper.MessageMapper;
import com.react.sample.service.MessageService;
import com.react.sample.service.vo.MessageVO;

@Service(value="MessageService")
public class MessageServiceImpl implements MessageService{
	@Autowired
	MessageMapper messageMapper;

	@Override
	public List<MessageVO> messageList(String recieveId) {
		// TODO Auto-generated method stub
		return messageMapper.messageList(recieveId);
	}

}
