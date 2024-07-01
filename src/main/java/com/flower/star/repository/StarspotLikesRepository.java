package com.flower.star.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.Member;
import com.flower.star.entity.Starspot;
import com.flower.star.entity.StarspotLikes;

@Repository
public interface StarspotLikesRepository extends JpaRepository<StarspotLikes, Integer>{
	StarspotLikes findByMemberAndStarspot(Member member, Starspot starspot);
}
