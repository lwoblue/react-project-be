package com.react.sample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.react.sample.service.MessageService;
import com.react.sample.service.vo.MessageVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	MessageService messageService;
	
	@GetMapping("/{userId}")
	public List<MessageVO> messageList(@PathVariable String userId) {
		System.out.println("messageList!!");
		return messageService.messageList(userId);
	}
}
