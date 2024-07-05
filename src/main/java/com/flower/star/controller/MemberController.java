package com.flower.star.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.flower.star.dto.MemberRegisterDTO;
import com.flower.star.dto.MemberUpdateDTO;
import com.flower.star.entity.Member;
import com.flower.star.entity.StarspotLikes;
import com.flower.star.repository.BoardRepository;
import com.flower.star.repository.StarspotLikesRepository;
import com.flower.star.entity.Board;
import com.flower.star.service.BoardSerivce;
import com.flower.star.service.MemberService;
import com.flower.star.utilities.Common;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final Common common;
	private final MemberService mService;
	private final BoardRepository bRepository;

	// 내 정보 삭제하기
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
					return "/member/updateProfile";
				}
				mService.updateProfile(member, common.getLoginUsername());
				return "redirect:/member/myProfile";
				
			} catch (Exception exception) {
				System.out.println(":::::::::ErrorMessage:"+exception.getMessage());
				model.addAttribute("error", exception.getMessage());
				return "/member/updateProfile";
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
			// UserDetails로 member 객체 가져오기
			Member member = mService.findByUsername(common.getLoginUsername());
			model.addAttribute("member", member);
			
			// Member 객체를 통해서 내가 쓴 게시물 리스트 가져오기
			List<Board> myBoardList = member.getBoardList();
			// 게시글 순서 역순으로 
			Collections.reverse(myBoardList);
			myBoardList.stream().limit(5).collect(Collectors.toList());
			model.addAttribute("myBoardList", myBoardList);
			
			// 좋아요 List 가져오기
			List<StarspotLikes> likeList = getLikesList();
			model.addAttribute("likeList", likeList);
			
			return "/member/myProfile";
			
		} catch (Exception exception) {
			
			return "redirect:/";
		}
	}
	
	
	@Autowired
	private StarspotLikesRepository starspotLikesRepository;
	
	public List<StarspotLikes> getLikesList() {
		try {
			Member member = mService.findByUsername(common.getLoginUsername());
			List<StarspotLikes> likeList = starspotLikesRepository.findByMember(member);
			
			return likeList;
		} catch(Exception e) {
			return null;
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
			model.addAttribute("warning", "아이디 또는 비밀번호가 일치하지 않습니다.");
			
		}
		return "/member/login";
	}
	
}
