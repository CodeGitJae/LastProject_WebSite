package com.flower.star.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.star.entity.Starspot;
import com.flower.star.repository.StarspotRepository;

@Service
public class MainService {

	@Autowired
	private StarspotRepository starspotRepository;
	
	public List<Starspot> getTop5Starspot() {
		
		return starspotRepository.findTop5ByOrderByViewsDesc();
	}
	
}
