package com.flower.star.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.Observatory;

@Repository
public interface ObservatoryRepository extends JpaRepository<Observatory, Integer> {

}
