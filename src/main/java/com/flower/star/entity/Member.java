package com.flower.star.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.flower.star.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="member")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Member {

	@Id  //pk 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment
	private long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Column(nullable = false, unique = true)
	private String nickname;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	
	public static Member toMember(MemberDTO mDTO) {
		Member member = new Member();
		
		member.setId(mDTO.getId());
		member.setUsername(mDTO.getUsername());
		member.setPassword(mDTO.getPassword());
		member.setNickname(mDTO.getNickname());
		member.setEmail(mDTO.getEmail());
		
		return member;
	}



}
