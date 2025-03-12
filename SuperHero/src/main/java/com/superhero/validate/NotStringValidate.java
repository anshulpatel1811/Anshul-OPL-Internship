package com.superhero.validate;

public class NotStringValidate implements jakarta.validation.ConstraintValidator<NotString, String>{

	@Override
	public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		//logic
		
		return value instanceof String ? true : false;
	}



}
