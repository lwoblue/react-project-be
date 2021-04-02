package com.react.sample.mapper;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.react.sample.service.vo.MessageVO;

@Repository
public interface MessageMapper {
	public List<MessageVO> messageList(String userId);
}
