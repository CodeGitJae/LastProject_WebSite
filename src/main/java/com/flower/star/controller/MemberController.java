package com.flower.star.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class MemberController {

	@Autowired
	private MemberService mService;
	
	@PostMapping("/member/duplicateCheck")
	@ResponseBody
	public String duplicateCheck(@RequestParam("username") String username) {
//		System.out.println(username);	
		String checkResult = mService.duplicateCheck(username);
		
		return checkResult;
	}
	
	
	@GetMapping("/member/delete/{id}")
	public String deleteById(@PathVariable int id) {
		mService.deleteById(id);
		
		return "/index";
	}
	
	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDTO mDTO) {
		mService.update(mDTO);
//		System.out.println(mDTO.getId());
		
		return "redirect:/member/myProfile/" + mDTO.getId();
	}
	
	@GetMapping("/member/update")
	public String updateForm(HttpSession session, Model model) {
	
		Integer myId = (Integer) session.getAttribute("loginId");
		MemberDTO mDTO = mService.updateForm(myId);
		model.addAttribute("updateMember", mDTO);
		
		return "/member/updateProfile";
	}
	
	@GetMapping("/member/myProfile/{loginId}")
	public String myProfile(@PathVariable("loginId") int id, Model model) {
	
//		System.out.println(id);
		
		MemberDTO member= mService.findById(id);
		model.addAttribute("member", member);
		
		return "/member/myProfile";
	}
	
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
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
	
	@GetMapping("member/login")
	public String loginForm() {
		return "/member/login";
	}

	
	@PostMapping("/member/signup")
	public String signUp(@ModelAttribute MemberDTO mDTO) {
//		System.out.println(mDTO);
		mService.save(mDTO);

		return "/member/login";
	}
	
	
	@GetMapping("/member/signup")
	public String signUpForm() {
		
		return "/member/signup";
	}

}
