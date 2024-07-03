package com.flower.star.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.StarspotReply;

@Repository
public interface StarspotReplyRepository extends JpaRepository<StarspotReply, Integer> {

	@Modifying
    @Query("UPDATE StarspotReply e SET e.content = :content WHERE e.id = :id")
    void updateContent(@Param("id") Integer id, @Param("content") String content);
}
