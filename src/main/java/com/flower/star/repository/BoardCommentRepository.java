package com.flower.star.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flower.star.entity.Board;
import com.flower.star.entity.BoardComment;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Integer> {

	List<BoardComment> findByBoard(Board board);





}
