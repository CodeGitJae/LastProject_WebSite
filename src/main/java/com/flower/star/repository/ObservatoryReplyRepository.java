package com.flower.star.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.ObservatoryReply;
import com.flower.star.entity.StarspotReply;

@Repository
public interface ObservatoryReplyRepository extends JpaRepository<ObservatoryReply, Integer> {

	@Modifying
    @Query("UPDATE ObservatoryReply e SET e.content = :content WHERE e.id = :id")
    void updateContent(@Param("id") Integer id, @Param("content") String content);
}
