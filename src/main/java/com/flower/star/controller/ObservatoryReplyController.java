package com.flower.star.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flower.star.entity.ObservatoryReply;
import com.flower.star.repository.ObservatoryReplyRepository;
import com.flower.star.service.ObservatoryReplyService;

@RestController
@RequestMapping("/observatory/reply")
public class ObservatoryReplyController {
	
	@Autowired
	private ObservatoryReplyService observatoryReplyService;
	
	@Autowired
	private ObservatoryReplyRepository observatoryReplyRepository;

	@PostMapping
	public Map<String, String> insert(@RequestBody ObservatoryReply observatoryReply, Integer boardid) {

		observatoryReplyService.insert(observatoryReply, boardid);
		
		Map<String, String> map = new HashMap<>();
		
		map.put("id", observatoryReply.getId().toString());
		map.put("content", observatoryReply.getContent());
		map.put("writer", observatoryReply.getWriter());
		map.put("createdate", observatoryReply.getCreatedate());
		
		return map;
	}
	
	@DeleteMapping
	public void delete(@RequestParam Integer id) {
		observatoryReplyRepository.deleteById(id);
		
	}
	
	@PutMapping
	@Transactional
	public void update(@RequestBody Map<String, String> payload) {
		Integer id = Integer.parseInt(payload.get("id"));
        String content = payload.get("content");
        
		System.out.println(id);
		System.out.println(content);
		
		observatoryReplyRepository.updateContent(id, content);		
	}
}
