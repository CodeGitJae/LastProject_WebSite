package com.flower.star.dto;

import com.flower.star.entity.Member;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

	private Integer id;
	private String username;
	private String password;
	private String confirmPassword;
	private String nickname;
	private String email;
	
	public static MemberDTO toMemberDTO(Member member) {
		MemberDTO mDTO = new MemberDTO();
		
		mDTO.setId(member.getId());
		mDTO.setUsername(member.getUsername()); 
		mDTO.setPassword(member.getPassword());
		mDTO.setConfirmPassword(member.getConfirmPassword());
		mDTO.setNickname(member.getNickname());
		mDTO.setEmail(member.getEmail());

		return mDTO;
	}	
}
