package com.flower.star.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.flower.star.enums.MemberType;
import com.flower.star.validators.annotations.EqualToValue;
import com.flower.star.validators.annotations.MemberUniqueCheck;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualToValue(first = "password", second="confirmPassword", message="비밀번호가 일치 하지 않습니다.")
public class MemberUpdateDTO {

	@NotBlank(message = "비번을 입력해 주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "한글, 영문, 숫자만 입력 가능합니다. (특수문자 입력 불가)")
    @Size(min = 3, max = 10, message = "3 ~ 10자로 입력해 주십시오.")
    private String password;
	
	@NotBlank(message="비번이 일치 하지 않습니다.")
	private String confirmPassword;
	
	@NotBlank(message = "닉네임(별명)을 입력해 주십시오.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣 ]*$", message = "한글, 영문, 숫자만 입력 가능합니다. (특수문자 입력 불가)")
    @Size(min = 3, max = 10, message = "3 ~ 10자로 입력해 주십시오.")
	@MemberUniqueCheck(dataType = MemberType.NICKNAME, message="이미 존재하는 닉네임(별명) 입니다.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해 주십시오.")
    @Email(message = "올바른 이메일을 입력해 주십시오.")
    @MemberUniqueCheck(dataType = MemberType.EMAIL, message = "이미 가입된 회원입니다.")
    private String email;
}
