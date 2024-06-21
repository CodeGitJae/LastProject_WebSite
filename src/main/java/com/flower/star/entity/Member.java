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
@ToString
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique= true, length= 10)
	private String username;
	private String password;
	@Transient
	private String confirmPassword;
	
	@Column(length= 20)
	private String nickname;
	@Column(length= 30)
	private String email;
	
	
	@Builder
	public static Member toMember(MemberDTO mDTO) {
		Member member = new Member();
		
		member.id = member.getId();
		member.username = member.getUsername();
		member.password = member.getPassword();
		member.confirmPassword = member.getConfirmPassword();
		member.nickname = member.getNickname();
		member.email = member.getEmail();
		
		return member;
	}
}
