package com.flower.star.dto;

import com.flower.star.entity.Board;
import com.flower.star.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {

	private Integer id;
	private String title;
	private String content;
	private String createDate;
	private String updateDate;
	private int views;
	
	public static BoardDTO toBoard(Board board) {
		BoardDTO bDTO = new BoardDTO();
		
		bDTO.setId(board.getId());
		bDTO.setTitle(board.getTitle());
		bDTO.setContent(board.getContent());
		bDTO.setCreateDate(board.getCreateDate());
		bDTO.setUpdateDate(board.getUpdateDate());
		bDTO.setViews(board.getViews());
		
		return bDTO;
	}
	
}
