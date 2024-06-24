package com.flower.star.validators;

import org.springframework.stereotype.Component;

import com.flower.star.enums.MemberType;
import com.flower.star.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServerValidator {

	private final MemberRepository mRepository;	
	
	public boolean isUnique(MemberType type, String value) {
		
		return !switch(type) {
		case USERNAME -> mRepository.existsByUsername(value);
		case NICKNAME -> mRepository.existsByNickname(value);
		case EMAIL -> mRepository.existsByEmail(value);
		};
	}
}
