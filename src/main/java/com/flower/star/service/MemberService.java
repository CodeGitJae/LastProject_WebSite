package com.flower.star.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flower.star.dto.MemberDTO;
import com.flower.star.entity.Member;
import com.flower.star.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository mRepository;

	
	// 아이디 삭제
	public void deleteById(long id) {
		mRepository.deleteById(id);
		
	}
	
	
	// 아이디 중복 체크 / DB에 중복된 값이 있는지 확인
	public String duplicateCheck(String username) {
		Optional<Member> checkedUser = mRepository.findByUsername(username);
		
		if(checkedUser.isPresent())
		{
			// 조회 결과에 데이터가 있을 때
			return null;
		}else {
			// 조회 결과에 데이터가 없을 때
			return "enabled";
		}
	}

	
	// 클라이언트에서 보낸 수정 데이터 저장하기
	public void update(MemberDTO mDTO) {
		mRepository.save(Member.toMember(mDTO));
		
	}
	
	
	// 회원정보 수정 화면에 보여줄 데이터 db에서 가져오기
	public MemberDTO updateForm(long myId) {
		Optional<Member> optionalMember =  mRepository.findById(myId);
		
		if(optionalMember.isPresent()) {
			return MemberDTO.toMemberDTO(optionalMember.get());
		
		} else {
			// 데이터가 없을 때 null로 처리
			return null;
		}
	}

	
	// 회원 상세보기
	public MemberDTO findById(long id) {
			
		Optional<Member> optionalMember = mRepository.findById(id);
		if(optionalMember.isPresent()) {
			return MemberDTO.toMemberDTO(optionalMember.get());
		
		} else {
			// 데이터가 없을 때 null로 처리
			return null;
		}
	}
	
	
	// 로그인을 위해 db 데이터 가져오기
	public MemberDTO login(MemberDTO mDTO) {
		
		Optional<Member> username = mRepository.findByUsername(mDTO.getUsername());          
		
		if(username.isPresent()) {
			//조회 결과가 true
			Member member =username.get();
			if(member.getPassword().equals(mDTO.getPassword())){
				//비밀번호 일치
				// entity -> dto 객체로 변환 후 리턴
				MemberDTO dto = MemberDTO.toMemberDTO(member);
				return dto;
				
			} else {
				//비밀번호 불일치(로그인실패)
				return null;
			}
		} else {
			// 조회 결과 false
			return null;
		}
	}

	
	// 회원 가입 DB 테이블에 데이터 저장 
	public void save(MemberDTO mDTO) {
		Member member = Member.toMember(mDTO);
		mRepository.save(member);
		
	}

}
