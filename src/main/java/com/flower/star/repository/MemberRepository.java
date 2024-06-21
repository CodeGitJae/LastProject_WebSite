package com.flower.star.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

}
