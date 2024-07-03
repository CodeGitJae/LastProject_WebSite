package com.flower.star.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.flower.star.entity.Board;
import com.flower.star.service.BoardSerivce;
import com.flower.star.service.MemberService;
import com.flower.star.util.Pagination;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final MemberService mService;
	private final BoardSerivce bService;
	

	 // 글작성 화면 띄워주기
	@GetMapping("/write")
	public String showWrite() {
		return "/board/write";
	}
	
	
	// 입력된 게시물 정보 저장 / 이미지가 있는 경우도 포함
	@PostMapping("/write")
	public String writeBoard(@RequestParam("username") String mUsername, Board board, MultipartFile[] uploadToBoardImage, RedirectAttributes redirectAttributes) {
		try {
			// 로그인되어 있는 인증 정보를 받아 member 테이블에 DB 저장
			board.setMember(mService.findByUsername(mUsername));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		try {
			bService.insert(board);
			bService.insertImage(board, uploadToBoardImage);
//			System.out.println(":::::::::::::::controller :" + board);
//			System.out.println(":::::::::::::::controller :" +uploadToBoardImage);			

            // 성공적으로 업로드되었을 경우
            redirectAttributes.addFlashAttribute("message", "파일 업로드가 성공적으로 완료되었습니다.");
            return "redirect:/board/list";
          
            // 사용자 예외 처리  
		} catch (MaxUploadSizeExceededException exc) {
			redirectAttributes.addFlashAttribute("errorMessage", "파일 크기가 너무 큽니다. 최대 허용 크기는 20MB입니다.");
			return "redirect:/board/list";
		}
	}
	
	
	@GetMapping("/list")
	public String paging(@RequestParam(name="page", defaultValue = "1") int curPage, Model model) {

		// 한 페이지당 출력 게시물 수 
		int blockLimit = 5;
		Page<Board> paging = bService.paging(curPage -1);
		Pagination pagination = new Pagination(curPage, paging.getTotalPages(), blockLimit);
//		int nextKey = (int) (Math.floor((curPage - 1) / 5) * 5 + 6);
//		int prevKey = (int) (Math.floor((curPage - 1) / 5) * 5);
		
		model.addAttribute("paging", paging);
		model.addAttribute("pagination", pagination);
//		model.addAttribute("nextKey", nextKey);
//		model.addAttribute("prevKey", prevKey);
		
		return "/board/list";
		
	}
	
//	// 게시판 목록 보여주기
//	@GetMapping("")
//	public String save(Model model) {
//		
//		List<Board> board = bService.findAll();
//		model.addAttribute("board", board);
//		
//		return "/board/list";
//	}
	
	
	@GetMapping("/detail")
	public String BoardDetails(@RequestParam("id") Integer bId, Model model) {
//		System.out.println("::::::::::::::::bid: "+bId);
		Board board = bService.findById(bId);
		bService.updateViews(bId);
		
		model.addAttribute("b", board);
		return"/board/detail";
	}
	
	@GetMapping("/update")
	public String updateBoard(@RequestParam("id") Integer bId, Model model) {
		
		Board board = bService.findById(bId);
		model.addAttribute("b", board);
		return "/board/update";
	}
	
	
	
	@PostMapping("/update")
	public String updateBoard(Board board, MultipartFile[] uploadToBoardImage) {

		bService.update(board, uploadToBoardImage);

		return "redirect:/board/detail?id="+ board.getId();
	}
	
	@GetMapping("/delete")
	public String deleteToBoard(@RequestParam("id") Integer id) {
		if(id == null) {
			return "redirect:/board/detail?id=" + id;
		}
		bService.deleteById(id);
		return "redirect:/board/list";
	}
	
}
