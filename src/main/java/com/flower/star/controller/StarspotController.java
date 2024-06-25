package com.flower.star.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.flower.star.util.Pagination;



@Controller
@RequestMapping("/starspot")
public class StarspotController {
	
	@Autowired
	private StarspotService starspotService;
	
	@Autowired
	private StarspotRepository starspotRepository;

	@GetMapping("")
	public String starspotList(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String sortField) {

		Page<Starspot> paging = starspotService.find(page, sortField);
		Pagination pagination = new Pagination(page, paging.getTotalPages(), 5);
		
		model.addAttribute("paging", paging);
		model.addAttribute("sortField", sortField);
		model.addAttribute("pagination", pagination);
		
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
		
		s.setViews(s.getViews() + 1);
		
		starspotRepository.save(s);
		
		model.addAttribute("data", s);
		
		return "/starspot/detail";
	}
}
