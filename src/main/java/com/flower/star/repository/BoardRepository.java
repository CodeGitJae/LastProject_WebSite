package com.flower.star.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.flower.star.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

//	List<Board> findAll();
	Page<Board> findAll(Pageable pageable);
	
	// update board_table set board_views=board_views+1 where id=?
	// JSA에서 제공하는 Query 어노테이션을 사용해서 뷰수를 업데이트함 이번엔 테이블자리에 entity 객체를 사용함
	@Modifying
	@Query(value = "update Board b set b.views=b.views+1 where b.id=:id")
	void updateViews(@Param("id") Integer id);



}
