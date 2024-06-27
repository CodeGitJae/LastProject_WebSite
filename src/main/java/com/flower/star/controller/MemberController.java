package com.flower.star.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flower.star.dto.MemberRegisterDTO;
import com.flower.star.dto.MemberUpdateDTO;
import com.flower.star.service.MemberService;
import com.flower.star.utilities.Common;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final Common common;
	private final MemberService mService;

	
	@GetMapping("/member/delete")
	public String deleteById(@RequestParam("username") String username) {

		if(username != null) {
			mService.deleteByUsername(username);
			return "redirect:/member/logout";
		} else {
			return "redirect:/member/myProfile";
		}
	}
	
	 // 마이페이지 내 정보 수정하기
	@PostMapping("/member/update/processedDone")
    public String updateProfile(@ModelAttribute("member") @Valid MemberUpdateDTO member,
    									BindingResult bindingResult, Model model) {
	 
//		 System.out.println("::::::::::member"+member);
//		 System.out.println("::::::::::member"+bindingResult);
	 		try {
				if(bindingResult.hasErrors()) {
					return "member/updateProfile";
				}
				mService.updateProfile(member, common.getLoginUsername());
				return "redirect:/member/myProfile";
			} catch (Exception exception) {
				System.out.println(":::::::::ErrorMessage:"+exception.getMessage());
				model.addAttribute("error", "처리 중 오류가 발생 했습니다.");
				return "redirect:/";
			}
	 }	 		
		 
	 
	  // 마이페이지 내 정보 가져오기
	  @GetMapping("/member/update")
	  public String update(Model model) {
		
			try {  
				model.addAttribute("member", mService.showCurrentInformation(common.getLoginUsername()));
				return "/member/updateProfile";
			} catch (Exception exception) {
				return "redirect:/member/logout";
			}
	  }		 		

	
	// 내 회원 정보 자세히보기
	@GetMapping("/member/myProfile")
	public String myProfile(Model model) {
		try {
			model.addAttribute("member", mService.findByUsername(common.getLoginUsername()));
			return "/member/myProfile";
		} catch (Exception exception) {
			return "redirect:/index";
		}
	}
	

	// 입력된 회원정보 유효성 검사후 정상일 때 서비스단으로 데이터 넘김
	@PostMapping("/member/signup")
	public String signUpValidation(@ModelAttribute("member") @Valid MemberRegisterDTO member,
									BindingResult bindingResult, Model model) {

//		System.out.println(member);
//		System.out.println(bindingResult);
		try {
			if (bindingResult.hasErrors()) {
				return "/member/signup";
			}

			mService.register(member);
			return "redirect:/member/login";
		} catch (Exception exception) {
			model.addAttribute("error", exception.getMessage());
			return "member/signup";
		}
	}

	
	// 회원 가입 페이지 띄워주는 라우터
	@GetMapping("/member/signup")
	public String signUp(Model model) {
		model.addAttribute("member", new MemberRegisterDTO());

		return "/member/signup";
	}

	
	// 로그인 페이지로 이동
	@GetMapping("/member/login")
	public String loginForm(@RequestParam(name = "status", defaultValue = "") String status, Model model) {
		
	//	System.out.println(":::::::::" + status);

		if (status.equals("error")) {
			model.addAttribute("warning", "아이디가 존재하지 않습니다.");
			
		}
		return "/member/login";
	}
	
}
	
//// 아이디 중복 체크 라우터
//@PostMapping("/member/duplicateCheck")
//@ResponseBody
//public String duplicateCheck(@RequestParam("username") String username) {
////	System.out.println(username);	
//	String checkResult = mService.duplicateCheck(username);
//	
//	return checkResult;
//}

//// 회원 탈퇴 라우터
//@GetMapping("/member/delete/{id}")
//public String deleteById(@PathVariable long id, HttpSession session) {
//	mService.deleteById(id);
//	session.invalidate();
//	
//	return "redirect:/";
//}
//
//// 새로운 회원정보로 수정
//@PostMapping("/member/update")
//public String update(@ModelAttribute MemberDTO mDTO) {
//	mService.update(mDTO);
////	System.out.println(mDTO.getId());
//	
//	return "redirect:/member/myProfile/" + mDTO.getId();
//}
//}
//// 기존 회원정보 클라이언트에 표시
//@GetMapping("/member/update/{id}")
//public String updateForm(@PathVariable long id, Model model) {
//
//MemberDTO mDTO = mService.updateForm(id);
//model.addAttribute("updateMember", mDTO);
//
//return "/member/updateProfile";
//}

//// 로그아웃 라우터
//@GetMapping("/member/logout")
//public String logout(HttpSession session) {
//	session.invalidate();
//	
//	return "redirect:/";
//}

//// index 페이지 메뉴바 로그인 라우터
//	@PostMapping("/member/login")
//	public String login(@ModelAttribute MemberDTO mDTO, HttpSession session, Model model) {
//		MemberDTO loginResult = mService.login(mDTO);
//		
//		System.out.println(":::::::::::::::"+loginResult);
//		
//		if(loginResult != null) {
//			//login 성공 시
//			session.setAttribute("loginUsername", loginResult.getUsername());
//			return "/index";
//		} else {
//			//login 실패 시
//			return "/member/login";
//		}
//	}
