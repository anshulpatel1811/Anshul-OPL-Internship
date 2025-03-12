package com.superhero.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidLongValidate implements ConstraintValidator<ValidLong, Long>{

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		String ans = value.toString();
		// logic
		if(ans!=null && !ans.isEmpty())
			return true;
		else
			return false;
	}

}
