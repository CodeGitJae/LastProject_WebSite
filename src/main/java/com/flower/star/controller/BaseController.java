//package com.flower.star.controller;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//
//import com.flower.star.entity.Member;
//import com.flower.star.service.MemberService;
//
//import lombok.RequiredArgsConstructor;
//
//@ControllerAdvice
//@RequiredArgsConstructor
//public class BaseController {
//
////	private final MemberService mService;
//	
//	@ModelAttribute
//	public void commond(Model model, Authentication authentication) {
//		String name = "";
//		
//		if(authentication != null) {
//		name = authentication.getName();
//		}
//		
////		System.out.println("::::::::::Member"+member);		
//		model.addAttribute("member", name);
//	}
//}

// 이 controller는 사용 하면 안됨. 데이터 베이스 끼리 충돌이 생기는것 같음.
// 추후 사용가능한 부분에서 사용하기 위해서 남겨 놓았으니 지우지는 마세요.
