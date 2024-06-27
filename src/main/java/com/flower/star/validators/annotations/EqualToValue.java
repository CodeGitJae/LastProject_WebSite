package com.flower.star.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.beans.BeanWrapperImpl;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EqualToValue.Validator.class)
public @interface EqualToValue {

	String message() default "입력된 두 값이 일치하지 않습니다.";
	
	Class<?>[] groups() default {};
	Class<? extends Payload> [] payload() default {};
	
	String first();
	String second();
	
	
	class Validator implements ConstraintValidator<EqualToValue, Object> {
		
		private String firstFieldName;
		private String secondFieldName;
		private String message;
		
		
		@Override
		public void initialize(EqualToValue constraintAnnotation) {
			firstFieldName = constraintAnnotation.first();
			secondFieldName = constraintAnnotation.second();
			message = constraintAnnotation.message();
		}
		
		@Override
		public boolean isValid(final Object value, final ConstraintValidatorContext context) {
			boolean vaild = true;
			
			try {
				final Object firstObject = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
				final Object secondObject =  new BeanWrapperImpl(value).getPropertyValue(secondFieldName);
				vaild = firstObject == null && secondObject == null || firstObject.equals(secondObject) && firstObject != null ;
			} catch (final Exception ignore) {
				// ignore 처리
			}	
			
			if(!vaild) {
				context.buildConstraintViolationWithTemplate(message)
						.addPropertyNode(firstFieldName)
						.addConstraintViolation()
						.disableDefaultConstraintViolation();
			}
			
			return vaild;
		}
		
	}
	
}
