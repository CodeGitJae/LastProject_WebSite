package com.flower.star.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/observatory")
public class ObservatoryController {

	@GetMapping("")
	public String observatory() {

		return "/observatory/board";
	}
	
}
