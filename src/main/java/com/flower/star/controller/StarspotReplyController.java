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

import com.flower.star.entity.StarspotReply;
import com.flower.star.repository.StarspotReplyRepository;
import com.flower.star.service.StarspotReplyService;

@RestController
@RequestMapping("/starspot/reply")
public class StarspotReplyController {
	
	@Autowired
	private StarspotReplyService starspotReplyService;
	
	@Autowired
	private StarspotReplyRepository starspotReplyRepository;

	@PostMapping
	public Map<String, String> insert(@RequestBody StarspotReply starspotReply, Integer boardid) {

		starspotReplyService.insert(starspotReply, boardid);
		
		Map<String, String> map = new HashMap<>();
		
		map.put("id", starspotReply.getId().toString());
		map.put("content", starspotReply.getContent());
		map.put("writer", starspotReply.getWriter());
		map.put("createdate", starspotReply.getCreatedate());
		
		return map;
	}
	
	@DeleteMapping
	public void delete(@RequestParam Integer id) {
		starspotReplyRepository.deleteById(id);
		
	}
	
	@PutMapping
	@Transactional
	public void update(@RequestBody Map<String, String> payload) {
		Integer id = Integer.parseInt(payload.get("id"));
        String content = payload.get("content");
        
		System.out.println(id);
		System.out.println(content);
		
		starspotReplyRepository.updateContent(id, content);		
	}
}
