package com.flower.star.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.star.entity.Starspot;
import com.flower.star.entity.StarspotReply;
import com.flower.star.repository.StarspotReplyRepository;
import com.flower.star.repository.StarspotRepository;

@Service
public class StarspotReplyService {
	
	@Autowired
	private StarspotReplyRepository starspotReplyRepository;
	
	@Autowired
	private StarspotRepository starspotRepository;

	public void insert(StarspotReply starspotReply, Integer boardid) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(now);

		starspotReply.setCreatedate(date);
		
		Starspot starspot = starspotRepository.findById(boardid).get();
		starspotReply.setStarspot(starspot);
		
		starspotReplyRepository.save(starspotReply);
	}
}
