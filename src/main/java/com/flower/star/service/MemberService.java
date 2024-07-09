package com.flower.star.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flower.star.dto.MemberRegisterDTO;
import com.flower.star.dto.MemberUpdateDTO;
import com.flower.star.entity.Member;
import com.flower.star.enums.MemberType;
import com.flower.star.enums.RoleType;
import com.flower.star.repository.MemberRepository;
import com.flower.star.validators.ServerValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final PasswordEncoder passwordEncoder;
	private final ServerValidator validator;
	private final MemberRepository mRepository;
	private final RoleService rService;

	// 회원 정보 삭제 
	@Transactional
	public void deleteByUsername(String username) {
		mRepository.deleteByUsername(username);
	}

	// 회원 정보 수정 정보 중복 검사 처리 후 데이터 저장 
	public void updateProfile(MemberUpdateDTO member, String username) throws Exception{
		
		  Member updateMember = mRepository.findByUsername(username).orElseThrow(() -> new Exception("데이터가 존재 하지 않습니다."));
		  		  
		  if(!updateMember.getNickname().equals(member.getNickname())) {
			  //db에 저장된 데이터가 있는경우 값을 찾아올 때 반환된 false 값을 true로 재 반전 시킴	  
		  if(!validator.isUnique(MemberType.NICKNAME, member.getNickname())) throw new Exception("이 닉네임은 이미 사용중 입니다"); 
		  } 
		  
		  if(!updateMember.getEmail().equals(member.getEmail())) {
			// db에 저장된 데이터가 있는경우 값을 찾아올 때 반환된 false 값을 true로 재 반전 시킴	 
		  if(!validator.isUnique(MemberType.EMAIL, member.getEmail())) throw new Exception("이미 등록된 이메일 입니다"); 
		  }
		  
		  updateMember.setNickname(member.getNickname());
		  updateMember.setEmail(member.getEmail());
		  
		  if(member.getPassword() != null) {
		  String chagePassword = passwordEncoder.encode(member.getPassword());
		  updateMember.setPassword(chagePassword);
		  }
		  
		  mRepository.save(updateMember);
	}
	
	
	// 내 정보 수정 부분 기존 입력된 데이터값 찾아오기 (첫번째 findByUsername이랑 맵핑주소가 다름)
	public Member showCurrentInformation(String getMember) throws Exception{
		return mRepository.findByUsername(getMember).orElseThrow(() -> new Exception("데이터가 없습니다."));
	}
	
	
	// 회원 가입 유효성 검사 통과 시 저장(Member, Role)
	@Transactional
	public void register(MemberRegisterDTO memberDto) throws Exception {
		
		
		try {
			String password = passwordEncoder.encode(memberDto.getPassword());
			Member member = mRepository.save(
					Member.builder().username(memberDto.getUsername())
									.password(password)
									.nickname(memberDto.getNickname())
									.email(memberDto.getEmail())
									.build()
					);
					
					rService.add(RoleType.USER, member);
		} catch (DataAccessException exception) {
			throw new Exception("귀하의 정보를 처리하는 동안 문제가 발생했습니다.");
		}
				
	}

	// myPage 보여줄 User 정보 찾아오기
	public Member findByUsername(String username) throws Exception{
		return mRepository.findByUsername(username).orElseThrow(() -> new Exception("데이터가 없습니다."));
	}

}
