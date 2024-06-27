package com.flower.star.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.Observatory;


@Repository
public interface ObservatoryRepository extends JpaRepository<Observatory, Integer> {

	Page<Observatory> findAll(Pageable pageable);

	List<Observatory> findTop5ByOrderByViewsDesc();
}
