package com.flower.star.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.Starspot;

@Repository
public interface StarspotRepository extends JpaRepository<Starspot, Integer> {
	
}
