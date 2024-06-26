package com.flower.star.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

		
	@GetMapping("/")
	public String index(Authentication authentication, Model model) {
		model.addAttribute("access", authentication);
		
		return "/index";
	}
	
}
