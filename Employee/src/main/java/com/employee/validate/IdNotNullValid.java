package com.employee.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdNotNullValid implements ConstraintValidator<IdNotNull, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("Data--"+value);
		if(value!=null) {
			return true;
		}
		return false;
	}

}
