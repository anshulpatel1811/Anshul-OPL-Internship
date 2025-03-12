package com.category.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyDoubleValid implements ConstraintValidator<NotEmptyDouble, Double>{

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		

		 if (value > 0 && !Double.isNaN(value) && !Double.isInfinite(value)) {
		        return true;  // Input is valid
		    }
		return false;
	}

}
