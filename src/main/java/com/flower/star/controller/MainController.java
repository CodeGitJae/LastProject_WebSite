package com.flower.star.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flower.star.entity.Starspot;
import com.flower.star.service.MainService;


@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;

	
	@GetMapping("/")
	public String index(Model model) {
		
		List<Starspot> top5Starspot = mainService.getTop5Starspot();
		
		for(Starspot s : top5Starspot) {
			System.out.println(s.getTitle());
		}
		
		model.addAttribute("top5Starspot", top5Starspot);
		
		return "/index";
	}

	
	@GetMapping("/sample")
	public String sample() {
		
		return "/sample";
	}
	
}
