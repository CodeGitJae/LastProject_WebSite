package com.flower.star.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.flower.star.entity.Observatory;
import com.flower.star.repository.ObservatoryRepository;
import com.flower.star.service.ObservatoryService;
import com.flower.star.util.Pagination;



@Controller
@RequestMapping("/observatory")
public class ObservatoryController {
	
    @Autowired
    private ObservatoryService observatoryService;

    @Autowired
    private ObservatoryRepository observatoryRepository;

	@GetMapping("")
	public String observatory(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String sortField) {

		Page<Observatory> paging =  observatoryService.find(page, sortField);
		Pagination pagination = new Pagination(page, paging.getTotalPages(), 5);
		
		model.addAttribute("paging", paging);
		model.addAttribute("sortField", sortField);
		model.addAttribute("pagination", pagination);
		
		return "/observatory/board";
	}
	
	@GetMapping("/write")
	public String observatoryWrite() {
		
		return "/observatory/write";
	}
	
	
	@PostMapping("/write")
	public String observatoryWrite(Observatory observatory, MultipartFile[] uploadImages) {
		
		observatoryService.insert(observatory);
		observatoryService.insertImg(observatory, uploadImages);
		
		return "redirect:/observatory";
	}
	

	@GetMapping("/detail")
	public String observatoryDetail(Model model, @RequestParam Integer id) {
		
		Observatory ov = observatoryRepository.findById(id).get();
		
		ov.setViews(ov.getViews() + 1);
		observatoryRepository.save(ov);
		
		model.addAttribute("data", ov);
		
		return "/observatory/detail";
	}
	
}
