package com.flower.star.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flower.star.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	Optional<Member> findByUsername(String username);

	Optional<Member> findByEmail(String email);




}
