package com.flower.star.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.flower.star.dto.MemberDTO;
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
	
	
	// 아이디 삭제
	public void deleteById(long id) {
		mRepository.deleteById(id);
		rService.deleteById(id);
		
	}


	// 수정 
	public void updateProfile(MemberUpdateDTO member, String username) throws Exception{
		Member updateMember = mRepository.findByUsername(username).orElseThrow(() -> new Exception("데이터가 존재 하지 않습니다."));
		
		if(!updateMember.getNickname().equals(member.getNickname())) {
			if(validator.isUnique(MemberType.NICKNAME, member.getNickname())) throw new Exception("이 닉네임은 이미 사용중 입니다");
		}
		
		if(!updateMember.getEmail().equals(member.getEmail())) {
			if(validator.isUnique(MemberType.EMAIL, member.getEmail())) throw new Exception("이미 등록된 이메일 입니다");
		}
		
		updateMember.setNickname(member.getNickname());
		updateMember.setEmail(member.getEmail());
		
		mRepository.save(updateMember);
	}
	
	
	
	// 회원정보 수정 화면에 보여줄 데이터 db에서 가져오기
	public MemberDTO updateForm(long id) {
		Optional<Member> optionalMember =  mRepository.findById(id);
		
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


	
	// baseController db 값 가져오는 메서드
	public Member findByUsername(String name) {
		Optional<Member> optMember = mRepository.findByUsername(name);
		
		if(optMember.isPresent()) {
			Member member = optMember.get();
			return member;
		} else {
			return null;
		}
	
	}

//	
//	// 아이디 중복 체크 / DB에 중복된 값이 있는지 확인
//	public String duplicateCheck(String username) {
//		Optional<Member> checkedUser = mRepository.findByUsername(username);
//		
//		if(checkedUser.isPresent())
//		{
//			// 조회 결과에 데이터가 있을 때
//			return null;
//		}else {
//			// 조회 결과에 데이터가 없을 때
//			return "enabled";
//		}
//	}
//
//	
//	// 클라이언트에서 보낸 수정 데이터 저장하기
//	public void update(MemberDTO mDTO) {
//		mRepository.save(Member.toMember(mDTO));
//		
//	}
//	
//	
//	// 로그인을 위해 db 데이터 가져오기
//	public MemberDTO login(MemberDTO mDTO) {
//		
//		Optional<Member> username = mRepository.findByUsername(mDTO.getUsername());          
//		
//		if(username.isPresent()) {
//			//조회 결과가 true
//			Member member =username.get();
//			if(member.getPassword().equals(mDTO.getPassword())){
//				//비밀번호 일치
//				// entity -> dto 객체로 변환 후 리턴
//				MemberDTO dto = MemberDTO.toMemberDTO(member);
//				return dto;
//				
//			} else {
//				//비밀번호 불일치(로그인실패)
//				return null;
//			}
//		} else {
//			// 조회 결과 false
//			return null;
//		}
//	}
//
//	
//	// 회원 가입 DB 테이블에 데이터 저장 
//	public void save(MemberDTO mDTO) {
//		Member member = Member.toMember(mDTO);
//		mRepository.save(member);
//		
//	}

}
