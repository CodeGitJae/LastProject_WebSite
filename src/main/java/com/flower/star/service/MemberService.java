package com.flower.star.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.star.dto.MemberDTO;
import com.flower.star.entity.Member;
import com.flower.star.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private MemberRepository mRepository;

	public void save(MemberDTO mDTO) {
		Member member = Member.toMember(mDTO);
		mRepository.save(member);
		
	}
}
