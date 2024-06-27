package com.flower.star.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservatoryRepository extends JpaRepository<ObservatoryRepository, Integer> {

}
