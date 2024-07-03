package com.flower.star.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.star.entity.Observatory;
import com.flower.star.entity.ObservatoryReply;
import com.flower.star.repository.ObservatoryReplyRepository;
import com.flower.star.repository.ObservatoryRepository;

@Service
public class ObservatoryReplyService {
	
	@Autowired
	private ObservatoryReplyRepository observatoryReplyRepository;
	
	@Autowired
	private ObservatoryRepository observatoryRepository;

	public void insert(ObservatoryReply observatoryReply, Integer boardid) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(now);

		observatoryReply.setCreatedate(date);
		
		Observatory observatory = observatoryRepository.findById(boardid).get();
		observatoryReply.setObservatory(observatory);
		
		observatoryReplyRepository.save(observatoryReply);
	}
}
