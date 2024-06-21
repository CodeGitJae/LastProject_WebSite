package com.flower.star.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.flower.star.dto.MemberDTO;
import com.flower.star.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	@Autowired
	private MemberService mService;
	
	
	@PostMapping("/joinus")
	public String getJoin(@ModelAttribute MemberDTO mDTO) {
		
		System.out.println(mDTO);
		mService.save(mDTO);

		
		return "/index";
	}
	
	
	@GetMapping("/joinus")
	public String join() {
		
		return "/member/joinus";
	}

}
