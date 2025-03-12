package com.employee.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyDoubleValid implements ConstraintValidator<NotEmptyDouble, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		System.out.println("Is this String--"+value);
		return (value!=null && !value.isEmpty())?true:false;
	}

}
