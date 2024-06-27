package com.flower.star.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoonCalendar {

	@GetMapping("/mooncalendar")
	public String moonCalendar() {
		
		return "/mooncalendar/mooncalendar";
	}
	
}
