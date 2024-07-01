package com.flower.star.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flower.star.entity.Board;
import com.flower.star.entity.BoardComment;
import com.flower.star.entity.Member;
import com.flower.star.repository.BoardCommentRepository;
import com.flower.star.repository.BoardRepository;
import com.flower.star.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

	private final BoardCommentRepository bcRepository;	
	private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


   
    public Integer writeComment(BoardComment bcomment, Integer bId, String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("유저가 존재하지 않습니다."));
        Board board = boardRepository.findById(bId).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
//        BoardComment boardcomment = bcRepository.findByContent(bcomment);
//        String content = boardcomment.getContent();
        
        
        BoardComment result = BoardComment.builder()
                .content(bcomment.getContent())
                .board(board)
                .member(member)
                .build();
        bcRepository.save(result);

        return result.getId();
    }

   
    public List<BoardComment> commentList(Integer id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        List<BoardComment> comments = bcRepository.findByBoard(board);
        System.out.println(":::::::::::::::::::::comments::::::::::::::"+comments);
        return comments;
    }

   
//    public void updateComment(BoardComment bcomment, Integer commentId) {
//        BoardComment comment = bcRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
//        comment.setContent(bcomment.getContent());
//        bcRepository.save(comment);
//    }

   
    public void deleteComment(Integer commentId) {
        bcRepository.deleteById(commentId);
    }
	
}
