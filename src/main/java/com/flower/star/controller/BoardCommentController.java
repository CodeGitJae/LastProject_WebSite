package com.flower.star.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.flower.star.entity.BoardComment;
import com.flower.star.service.BoardCommentService;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/boardComment")
public class BoardCommentController {

	private final BoardCommentService bcService;
	
	@PostMapping("/detail")
	public String writeComment(@RequestParam(name="id") Integer bId, 
							   BoardComment bcomment, Authentication authentication) {
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println(":::::::::::user::::::::details"+userDetails.getUsername());
		bcService.writeComment(bcomment, bId, userDetails.getUsername());
		
		
		return "redirect:/board/detail?id="+ bId;
	}
	
	 /**
     * 댓글 수정
     *  id 게시물
     *  commentId 댓글 ID
     *  commentRequestDTO 댓글 정보
     *  게시물 상세 페이지
     */
//	@ResponseBody     //        업데이트는 유지보수 기간에 구현할 예정 덧글 ajax로 변경해야함.
//    @GetMapping("/update")
//    public String updateComment(@RequestParam(name="id") Integer id, 
//    							@RequestParam(name="commentId") Integer commentId, BoardComment bcomment) {
//    	
//    	System.out.println("---------------------------------"+id+"------------------------"+commentId);
//        bcService.updateComment(bcomment, commentId);
//        return "/board/detail?id=" + id + "&comment=" + commentId;
//    }

    /**
     * 댓글 삭제
     *  id 게시물
     *  commentId 댓글 ID
     *  해당 게시물 리다이렉트
     */
    @GetMapping("/delete")
    public String deleteComment(@RequestParam(name="id") Integer id, 
    							@RequestParam(name="commentId") Integer commentId) {
    	
    	System.out.println("들어옴 ---------------------------------");
    	bcService.deleteComment(commentId);
        return "redirect:/board/detail?id=" + id + "&commentId=" + commentId;
    }
    
}