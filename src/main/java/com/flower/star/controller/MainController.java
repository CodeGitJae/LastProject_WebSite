package com.flower.star.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	@GetMapping("/")
	public String index() {
		
		return "/index";
	}
	
	@GetMapping("/sample")
	public String sample() {
		return "sample";
	}
	
}
