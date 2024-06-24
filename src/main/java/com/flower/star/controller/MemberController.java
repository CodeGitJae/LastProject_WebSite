package com.flower.star.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flower.star.dto.MemberDTO;
import com.flower.star.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService mService;
	
	
	// 아이디 중복 체크 라우터
	@PostMapping("/member/duplicateCheck")
	@ResponseBody
	public String duplicateCheck(@RequestParam("username") String username) {
//		System.out.println(username);	
		String checkResult = mService.duplicateCheck(username);
		
		return checkResult;
	}
	
	// 회원 탈퇴 라우터
	@GetMapping("/member/delete/{id}")
	public String deleteById(@PathVariable long id, HttpSession session) {
		mService.deleteById(id);
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 새로운 회원정보로 수정
	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDTO mDTO) {
		mService.update(mDTO);
//		System.out.println(mDTO.getId());
		
		return "redirect:/member/myProfile/" + mDTO.getId();
	}
	
	// 기존 회원정보 클라이언트에 표시
	@GetMapping("/member/update")
	public String updateForm(HttpSession session, Model model) {
	
		Integer myId = (Integer) session.getAttribute("loginId");
		MemberDTO mDTO = mService.updateForm(myId);
		model.addAttribute("updateMember", mDTO);
		
		return "/member/updateProfile";
	}
	
	
	// 내 회원 정보 자세히보기
	@GetMapping("/member/myProfile/{loginId}")
	public String myProfile(@PathVariable("loginId") long id, Model model) {
	
//		System.out.println(id);
		
		MemberDTO member= mService.findById(id);
		model.addAttribute("member", member);
		
		return "/member/myProfile";
	}
	
	// 로그아웃 라우터
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	// index 페이지 메뉴바 로그인 라우터
	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDTO mDTO, HttpSession session, Model model) {
		MemberDTO loginResult = mService.login(mDTO);
		
		if(loginResult != null) {
			//login 성공 시
			session.setAttribute("loginUsername", loginResult.getUsername());
			session.setAttribute("loginId", loginResult.getId());
			return "/index";
		} else {
			//login 실패 시
			return "/member/login";
		}
	}
	
	
	// 회원 가입 후 별도 로그인 페이지로 이동
	@GetMapping("member/login")
	public String loginForm() {
		return "/member/login";
	}

	
	// 입력된 회원정보 저장 라우터
	@PostMapping("/member/signup")
	public String signUp(@ModelAttribute MemberDTO mDTO) {
//		System.out.println(mDTO);
		mService.save(mDTO);

		return "/member/login";
	}
	
	// 회원 가입 페이지 띄워주는 라우터
	@GetMapping("/member/signup")
	public String signUpForm() {
		
		return "/member/signup";
	}

}
