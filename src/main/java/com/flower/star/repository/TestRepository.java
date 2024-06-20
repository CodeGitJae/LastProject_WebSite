package com.flower.star.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer> {

}
