package com.flower.star.controller;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flower.star.entity.Member;
import com.flower.star.service.MemberService;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class BaseController {

	private final MemberService mService;
	
	@ModelAttribute
	public void commond(Model model, Authentication authentication) {
		String name = "";
		
		if(authentication != null) {
		name = authentication.getName();
		}
		
		Member member = mService.findByUsername(name);
//		System.out.println("::::::::::Member"+member);		
		model.addAttribute("member", member);
	}
}
