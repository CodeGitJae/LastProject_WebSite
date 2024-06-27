package com.flower.star.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.Starspot;

@Repository
public interface StarspotRepository extends JpaRepository<Starspot, Integer> {
		
	Page<Starspot> findAll(Pageable pageable);
	
	List<Starspot> findTop5ByOrderByViewsDesc();
}
