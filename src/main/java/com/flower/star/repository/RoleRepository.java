package com.flower.star.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flower.star.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,	Long> {
								
	List<Role> findAllByMember_Id(Long member);
	
}
