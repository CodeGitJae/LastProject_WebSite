package com.flower.star.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flower.star.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
