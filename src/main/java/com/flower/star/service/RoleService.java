package com.flower.star.service;

import org.springframework.stereotype.Service;

import com.flower.star.entity.Member;
import com.flower.star.entity.Role;
import com.flower.star.enums.RoleType;
import com.flower.star.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepository rRepository;
	
	public void add(RoleType role, Member member) {
		rRepository.save(
				Role.builder().role(role).member(member).build()
		);	
	}

	public void deleteById(long id) {
		rRepository.deleteById(id);
		
	}
}
