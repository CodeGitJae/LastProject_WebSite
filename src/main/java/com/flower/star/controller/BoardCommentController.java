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
		bcService.writeComment(bcomment, bId, userDetails.getUsername());
		
		
		return "redirect:/board/detail?id="+ bId;
	}
	

	@ResponseBody    
    @PostMapping("/update")
    public String updateComment(@RequestParam(name="content") String content, 
    							@RequestParam(name="commentId") int commentId,
    							@RequestParam(name="bId") int bId) {
    	
        bcService.updateComment(commentId, content);
        return "/board/detail?id=" + bId;
    }


    @GetMapping("/delete")
    public String deleteComment(@RequestParam(name="id") Integer id, 
    							@RequestParam(name="commentId") Integer commentId) {
    	
    	bcService.deleteComment(commentId);
        return "redirect:/board/detail?id=" + id + "&commentId=" + commentId;
    }
    
}