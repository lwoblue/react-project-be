package com.react.sample.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.react.sample.service.MessageService;
import com.react.sample.service.vo.MessageVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	MessageService messageService;
	
	@PostMapping("/{recipient}")
	public List<MessageVO> messageList(@PathVariable String recipient, @RequestBody Map req) {
		String searchInput = String.valueOf(req.get("searchInput"));
		return messageService.messageList(recipient, searchInput);
	}
	
	@GetMapping("/detail/{uuid}")
	public MessageVO detailMessage(@PathVariable int uuid) {
		return messageService.detailMessage(uuid);
	}
	
	@DeleteMapping("/{uuid}")
	public void deleteMessage(@PathVariable int uuid) {
		messageService.deleteMessage(uuid);
	}
	
	@PostMapping
	public void sendMessage(@RequestBody MessageVO message) {
		messageService.insertMessage(message);
	}
}
