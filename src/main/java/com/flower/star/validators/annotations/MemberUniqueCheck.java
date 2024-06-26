package com.flower.star.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import com.flower.star.enums.MemberType;
import com.flower.star.validators.ServerValidator;

import lombok.RequiredArgsConstructor;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MemberUniqueCheck.Validator.class)
public @interface MemberUniqueCheck {

	String message();
	
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
	
	MemberType dataType();
	
	@RequiredArgsConstructor
	class Validator implements ConstraintValidator<MemberUniqueCheck, String> {
		
		private MemberType type;
		private final ServerValidator validator;
		
		@Override
		public void initialize(MemberUniqueCheck constraintAnnotation) {
			type = constraintAnnotation.dataType();
		}
		
		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			return validator.isUnique(type, value);
		}
		
	}
	
}
