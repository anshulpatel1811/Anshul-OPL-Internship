package com.employee.validate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = NotEmptyDoubleValid.class)
@Target({ METHOD, FIELD,PARAMETER })
@Retention(RUNTIME)
public @interface NotEmptyDouble {

	String message() default "Enter Double Data is Invalid !!";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
