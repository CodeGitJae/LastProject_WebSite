package com.flower.star.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.flower.star.entity.Starspot;
import com.flower.star.repository.StarspotRepository;
import com.flower.star.service.StarspotService;



@Controller
@RequestMapping("/starspot")
public class StarspotController {
	
	@Autowired
	private StarspotService starspotService;
	
	@Autowired
	private StarspotRepository starspotRepository;

	@GetMapping("")
	public String starspotList(Model model) {

		List<Starspot> list = starspotRepository.findAll();
		
		model.addAttribute("list", list);
		
		return "/starspot/board";
	}
	
	@GetMapping("/write")
	public String starspotWrite() {
		
		return "/starspot/write";
	}
	
	@PostMapping("/write")
	public String starspotWrite(Starspot starspot, MultipartFile[] uploadImages) {
		
		starspotService.insert(starspot);
		
		starspotService.insertImg(starspot, uploadImages);
		
		return "redirect:/starspot";
	}
	
	
	@GetMapping("/detail")
	public String starspotDetail(Model model, @RequestParam Integer id) {
		
		Starspot s = starspotRepository.findById(id).get();
		
		model.addAttribute("data", s);
		
		return "/starspot/detail";
	}
}
