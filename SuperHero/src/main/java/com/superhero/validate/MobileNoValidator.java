package com.superhero.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MobileNoValidator implements ConstraintValidator<ValidMobileNo, Long>{

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		
		String s = Long.toString(value);
        return s != null && s.matches("[0-9]{10}");  // Example validation

	}

}
