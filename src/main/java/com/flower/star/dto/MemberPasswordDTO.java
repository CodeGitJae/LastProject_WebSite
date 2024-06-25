package com.flower.star.dto;


import javax.validation.constraints.*;
import com.flower.star.validators.annotations.EqualToValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualToValue(first = "newPassword", second = "confirmPassword", message = "비밀번호가 일치하지 않습니다.")
public class MemberPasswordDTO {

	@NotBlank(message = "기존 비밀번호 입력해 주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문, 숫자만 입력 가능합니다. (특수문자 입력 불가)")
    @Size(min = 3, max = 10, message = "3~10자로 입력해 주세요.")
    private String nowPassword;

    @NotBlank(message = "새 비밀번호를 입력해 주십시오.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문, 숫자만 입력 가능합니다. (특수문자 입력 불가)")
    @Size(min = 3, max = 10, message = "3~10자로 입력해 주십시오.")
    private String newPassword;

    @NotBlank(message = "기존 및 신규 비번 불일치")
    private String confirmPassword;
	
}
