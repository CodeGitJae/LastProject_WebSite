package com.flower.star.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flower.star.entity.Member;
import com.flower.star.entity.Starspot;
import com.flower.star.entity.StarspotLikes;
import com.flower.star.repository.MemberRepository;
import com.flower.star.repository.StarspotLikesRepository;
import com.flower.star.repository.StarspotRepository;

@RestController
@RequestMapping("/likes")
public class StarspotLikesController {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private StarspotRepository starspotRepository;
	
	@Autowired
	private StarspotLikesRepository starspotLikesRepository;
	

	@PostMapping("/insert")
	public void insertLikes(@RequestBody Map<String, String> payload, boolean isLike) {
		
		Integer starspotId = Integer.parseInt(payload.get("starspotid"));
		String username = payload.get("userid");
		
		Member member = memberRepository.findByUsername(username).get();
		Starspot starspot = starspotRepository.findById(starspotId).get();
		if(!isLike) {
			
			StarspotLikes starspotLikes = new StarspotLikes();
			
			starspotLikes.setMember(member);
			starspotLikes.setStarspot(starspot);
			
			starspotLikesRepository.save(starspotLikes);			
		}
		
		if(isLike) {
			StarspotLikes starspotLikes = starspotLikesRepository.findByMemberAndStarspot(member, starspot);
			starspotLikesRepository.deleteById(starspotLikes.getId());		
		}
	}
	
}
