package com.flower.star.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.flower.star.entity.Board;
import com.flower.star.service.BoardSerivce;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardSerivce bService;
	
	// 게시판 목록 보여주기
	@GetMapping("/list")
	public String save() {
		return "/board/list";
	}
	
	
	 // 글작성 화면 띄워주기
	@GetMapping("/write")
	public String showWrite() {
		return "/board/write";
	}
	
	 // 입력된 게시물 정보 저장 / 이미지가 있는 경우도 포함
	@PostMapping("/write")
	public String writeBoard(Board board, MultipartFile[] uploadToBoardImage) {
		bService.insert(board);
		bService.insertImage(board, uploadToBoardImage);
		System.out.println(":::::::::::::::controller :" + board);
		System.out.println(":::::::::::::::controller :" +uploadToBoardImage);
		return "redirect:/board/list";
	}
	
	
	
}
